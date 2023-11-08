package com.example.teamconnect.utils;

import com.example.teamconnect.entity.Employee;
import com.example.teamconnect.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class JwtAuthenticationProvider implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("entered loadUserByUserName method ");
        Employee employee = employeeRepository.findByEmail(username);
        if (employee == null) {
            Employee employee1 = employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
            logger.info("entered findByPhoneNumber method");
            return new org.springframework.security.core.userdetails.User(String.valueOf(employee1.getPhoneNumber()), employee1.getPassword(),
                    new ArrayList<>());
        }
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(),
                new ArrayList<>());
    }
}
