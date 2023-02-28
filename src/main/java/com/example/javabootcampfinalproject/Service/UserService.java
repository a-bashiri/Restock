package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.DTO.ManufacturerDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Manufacturer;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final CustomerService customerService;
    private final ManufacturerService manufacturerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Wrong Username or Password");
        return user;
    }

    public void registerManufacturer(ManufacturerDTO dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getPhoneNumber(), Role.MANUFACTURER,null,null);
        Manufacturer manufacturer = new Manufacturer(null, dto.getName(), dto.getCategory(), dto.getLocation(),user,null, null);
        String hashed = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashed);
        manufacturerService.addManufacturer(manufacturer);
        userRepository.save(user);
    }

    public void registerCustomer(CustomerDTO dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getPhoneNumber(),Role.CUSTOMER,null,null);
        Customer customer = new Customer(null, dto.getName(), dto.getLocation(),user, null);
        String hashed = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashed);
        customerService.addCustomer(customer);
        userRepository.save(user);
    }

    //TODO: only for admin
    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("ID not found",400);
        userRepository.delete(user);
    }

    public void updateManufacturer(Integer id, ManufacturerDTO dto){
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("ID not found",400);
        user.setUsername(dto.getUsername());
        String hashed = new BCryptPasswordEncoder().encode(dto.getPassword());
        user.setPassword(hashed);
        user.setPhoneNumber(dto.getPhoneNumber());
        manufacturerService.updateManufacturer(id,dto);
        userRepository.save(user);
    }

    public void updateCustomer(Integer id, CustomerDTO dto){
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("ID not found",400);
        user.setUsername(dto.getUsername());
        String hashed = new BCryptPasswordEncoder().encode(dto.getPassword());
        user.setPassword(hashed);
        user.setPhoneNumber(dto.getPhoneNumber());
        customerService.updateCustomer(id,dto);
        userRepository.save(user);
    }

    public User getInfo(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("ID not found",400);
        return user;
    }
}
