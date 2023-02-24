package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);
}
