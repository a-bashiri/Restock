package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.DTO.ManufacturerDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Manufacturer;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.CustomerRepository;
import com.example.javabootcampfinalproject.Repository.ManufacturerRepository;
import com.example.javabootcampfinalproject.Repository.UserRepository;
import lombok.Data;
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
    private final ManufacturerRepository manufacturerRepository;
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Wrong Username or Password");
        return user;
    }

    public void registerManufacturer(ManufacturerDTO dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getPhoneNumber(),"MANUFACTURER",null,null);
        Manufacturer manufacturer = new Manufacturer(null, dto.getName(), dto.getCategory(), dto.getLocation(),user);
        String hashed = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashed);
        manufacturerRepository.save(manufacturer);
        userRepository.save(user);
    }

    public void registerCustomer(CustomerDTO dto){
        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getPhoneNumber(),"CUSTOMER",null,null);
        Customer customer = new Customer(null, dto.getName(), dto.getLocation(),user);
        String hashed = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashed);
        customerRepository.save(customer);
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("ID not found",400);
        userRepository.delete(user);
    }


}
