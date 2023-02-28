package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.OrderDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.*;
import com.example.javabootcampfinalproject.Repository.OrdersRepository;
import com.example.javabootcampfinalproject.Utility.Enum.Action;
import com.example.javabootcampfinalproject.Utility.Enum.OrderStatus;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ManufacturerService manufacturerService;
    private final ProductDetailsService productDetailsService;
    private final SpecialRequestsService specialRequestsService;
    private final UserService userService;


    public Orders getOrder(Integer id){
        return ordersRepository.findOrdersById(id);
    }

    private List<Orders> getAllOrder(){
        return ordersRepository.findAll();
    }

    public List<Orders> getAllOrdersForUser(User user){

        if (user.getRole() == Role.CUSTOMER)
            return user.getCustomer().getOrders();
        else if (user.getRole() == Role.MANUFACTURER)
            return user.getManufacturer().getOrders();
        else if (user.getRole() == Role.ADMIN)
            return getAllOrder();
        else
            return new ArrayList<Orders>();
    }

    public List<Orders> getAllOrdersForUserWithStatus(User user, OrderStatus orderStatus){

        if (user.getRole() == Role.CUSTOMER)
            return findOrdersForCustomerWithStatus(user, orderStatus);
        else if (user.getRole() == Role.MANUFACTURER)
            return findOrdersForManufacturerWithStatus(user, orderStatus);
        else if (user.getRole() == Role.ADMIN)
            return findOrdersForAdminWithStatus(orderStatus);
        else
            return new ArrayList<Orders>();
    }


    private List<Orders> findOrdersForAdminWithStatus(OrderStatus status){
        return ordersRepository.findOrdersByStatus(status);
    }

    private List<Orders> findOrdersForCustomerWithStatus(User user, OrderStatus status){
        return ordersRepository.findOrdersByCustomer_IdAndStatus(user.getCustomer().getId(), status);
    }

    private List<Orders> findOrdersForManufacturerWithStatus(User user, OrderStatus status){
        return ordersRepository.findOrdersByManufacturer_IdAndStatus(user.getManufacturer().getId(), status);
    }

    public void addOrder(User user, OrderDTO orderDTO){
        OrderStatus orderStatus = OrderStatus.PENDING;
        Date creationDate = new Date();
        String destinationLocation = user.getCustomer().getLocation();
        Manufacturer manufacturer = manufacturerService.getManufacturer(orderDTO.getManufacturer_id());
        if (manufacturer == null)
            throw new ApiException("Manufacturer Id was not found", 404);
        List<SpecialRequest> specialRequests = specialRequestsService.convertToList(orderDTO.getSpecialRequests_ids());
        String sourceLocation = manufacturer.getLocation();
        Orders order = new Orders(null,
                orderStatus,
                0,
                sourceLocation,
                destinationLocation,
                creationDate,
                null,
                null,
                specialRequests,
                null,
                user.getCustomer(),
                manufacturer);
        ordersRepository.save(order);

        List<ProductDetail> productDetails = productDetailsService.convertToList(orderDTO, order);

        float totalPrice = specialRequestsService.calculatePrice(specialRequests);
        totalPrice += productDetailsService.calculatePrice(productDetails);
        order.setTotalPrice(totalPrice);

        ordersRepository.save(order);
    }


    private void updateStatus(Integer orderId, OrderStatus status){
        Orders order = ordersRepository.findOrdersById(orderId);
        order.setStatus(status);
        ordersRepository.save(order);
    }

    public void manufacturerUpdateStatus(User user, Integer orderId, OrderStatus newStatus){

        boolean hasCorrectStatus = false;

        Orders order = getOrder(orderId);
        if (order == null)
            throw new ApiException("Order not found", 404);

        if (order.getManufacturer().getId() != user.getManufacturer().getId())
            throw new ApiException("Unauthorized", 401);

        //From pending to accepted/rejected
        if ((newStatus == OrderStatus.IN_PROGRESS || newStatus == OrderStatus.REJECTED)
                && order.getStatus() == OrderStatus.PENDING) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        //From In_Progress to Fulfilled
        if ((newStatus == OrderStatus.FULFILLED) &&
                (order.getStatus() == OrderStatus.IN_PROGRESS)) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        if (!hasCorrectStatus)
            throw new ApiException("You are not allowed to change to this status", 401);
    }

    public void customerUpdateStatus(User user, Integer orderId, OrderStatus newStatus){

        boolean hasCorrectStatus = false;
        Orders order = getOrder(orderId);

        if (order == null)
            throw new ApiException("Order not found", 404);

        if (order.getCustomer().getId() != user.getCustomer().getId())
            throw new ApiException("Unauthorized", 401);

        //From PENDING to CANCELED
        if ((newStatus == OrderStatus.CANCELED)
                && order.getStatus() == OrderStatus.PENDING){
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        if (!hasCorrectStatus)
            throw new ApiException("You are not allowed to change to this status", 401);

    }

    private void adminUpdateStatus(User user, Integer orderId, OrderStatus newStatus) {

        boolean hasCorrectStatus = false;

        if (user.getRole() != Role.ADMIN)
            throw new ApiException("Unauthorized", 401);

        Orders order = getOrder(orderId);
        if (order == null)
            throw new ApiException("Order not found", 404);

        //From FULFILLED to OUT_FOR_DELIVERY
        if ((newStatus == OrderStatus.OUT_FOR_DELIVERY)
                && order.getStatus() == OrderStatus.FULFILLED) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        //From OUT_FOR_DELIVERY to DELIVERED or OUT_FOR_RETURNING
        if ((newStatus == OrderStatus.DELIVERED || (order.getStatus() == OrderStatus.OUT_FOR_DELIVERY)) &&
                (order.getStatus() == OrderStatus.OUT_FOR_DELIVERY)) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        //From DELIVERED or OUT_FOR_DELIVERY to OUT_FOR_RETURNING
        if ((newStatus == OrderStatus.OUT_FOR_RETURNING) &&
                ((order.getStatus() == OrderStatus.DELIVERED) || (order.getStatus() == OrderStatus.OUT_FOR_DELIVERY))) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        //From OUT_FOR_RETURNING to RETURNED
        if ((newStatus == OrderStatus.RETURNED) &&
                (order.getStatus() == OrderStatus.OUT_FOR_RETURNING)) {
            updateStatus(orderId, newStatus);
            hasCorrectStatus = true;
        }

        if (!hasCorrectStatus)
            throw new ApiException("You are not allowed to change to this status", 401);

    }

    //TODO: Admin Only
    public void deleteOrder(User user, Integer id){
        Orders order = ordersRepository.findOrdersById(id);
        if (order == null)
            throw new ApiException("ID Not found",400);

        if (user.getRole() != Role.ADMIN)
            throw new ApiException("Not authorized to delete this order",403);

        ordersRepository.delete(order);
    }

    public void manufacturerActionOnOrder(User user, Integer orderId, Action action){
        Orders order = getOrder(orderId);
        if (order == null)
            throw new ApiException("Order not found", 404);

        if (action == Action.ACCEPT){
            order.setApprovalDate(new Date());
            ordersRepository.save(order);
            manufacturerUpdateStatus(user, orderId, OrderStatus.IN_PROGRESS);
        } else if (action == Action.REJECT){
            manufacturerUpdateStatus(user, orderId, OrderStatus.REJECTED);
        } else if (action == Action.FULFILL){
            manufacturerUpdateStatus(user, orderId, OrderStatus.FULFILLED);
        } else
            throw new ApiException("Wrong action", 401);

    }

    public void customerActionOnOrder(User user, Integer orderId, Action action){
        Orders order = getOrder(orderId);
        if (order == null)
            throw new ApiException("Order not found", 404);

        if (action == Action.CANCEL)
            customerUpdateStatus(user, orderId, OrderStatus.CANCELED);
        else
            throw new ApiException("Wrong action", 401);
    }

    //TODO: Admin Only
    public void adminActionOnOrder(User user, Integer orderId, Action action){
        Orders order = getOrder(orderId);
        if (order == null)
            throw new ApiException("Order not found", 404);

        if (action == Action.PICKED_FOR_DELIVERY){
            adminUpdateStatus(user, orderId, OrderStatus.OUT_FOR_DELIVERY);
        } else if (action == Action.DELIVERED){
            order.setDeliverDate(new Date());
            ordersRepository.save(order);
            adminUpdateStatus(user, orderId, OrderStatus.DELIVERED);
        } else if (action == Action.PICKED_FOR_RETURNING){
            adminUpdateStatus(user, orderId, OrderStatus.OUT_FOR_RETURNING);
        } else if (action == Action.RETURNED){
            adminUpdateStatus(user, orderId, OrderStatus.RETURNED);
        } else {
            throw new ApiException("Wrong action", 401);
        }
    }

}
