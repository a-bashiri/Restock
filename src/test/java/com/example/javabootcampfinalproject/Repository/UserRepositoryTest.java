package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.UserRepository;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    User user1;
    User user2;

    @BeforeEach
    void setUp(){
        user1 = new User(1,"abdulaziz","123","054432353", Role.ADMIN,null,null);
        user2 = new User(2,"yousef","123","054432353", Role.ADMIN,null,null);

    }

    @Test
    public void findUserByIdTest(){
        userRepository.save(user1);
        User user = userRepository.findUserById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1);
    }

    @Test
    public void findUserByUsernameTest(){
        userRepository.save(user1);
        User user = userRepository.findUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);
    }


}
