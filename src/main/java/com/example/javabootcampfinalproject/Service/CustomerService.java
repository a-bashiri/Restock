package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomer(Integer id){
        return customerRepository.findCustomerById(id);
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, CustomerDTO dto){
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer == null)
            throw new ApiException("ID not found",400);
        oldCustomer.setName(dto.getName());
        oldCustomer.setLocation(dto.getLocation());
        customerRepository.save(oldCustomer);
    }
}
