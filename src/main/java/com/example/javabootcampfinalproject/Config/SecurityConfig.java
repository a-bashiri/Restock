package com.example.javabootcampfinalproject.Config;


import com.example.javabootcampfinalproject.Service.UserService;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final UserService userService;
    private final String[] adminAPIs = {
            "/api/v1/admin/**",
    };
    private final String[] customerAPIs = {
            "/api/v1/customer/**",
            "/api/v1/orders/create",
            "/api/v1/user/update/customer"
    };

    private final String[] manufacturerAPIs = {
            "/api/v1/manufacturer/**",
            "/api/v1/user/update/manufacturer",
            "/api/v1/orders/manufacturer/**"
    };

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST,"/api/v1/user/register/**").permitAll()
                    .requestMatchers(adminAPIs).hasAuthority(Role.ADMIN.toString())
                    .requestMatchers(customerAPIs).hasAuthority(Role.CUSTOMER.toString())
                    .requestMatchers(manufacturerAPIs).hasAuthority(Role.MANUFACTURER.toString())
                    .anyRequest().authenticated()
                    .and()
                    .logout().logoutUrl("/api/v1/auth/logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .and()
                .httpBasic();
        return http.build();
    }


}
