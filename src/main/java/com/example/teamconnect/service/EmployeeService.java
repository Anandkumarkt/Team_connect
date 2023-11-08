package com.example.teamconnect.service;

import com.example.teamconnect.dto.EmployeeDto;
import com.example.teamconnect.exception.DataAlreadyExistsException;
import com.example.teamconnect.exception.LoginFailedException;
import com.example.teamconnect.response.ResponseClass;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
     ResponseEntity<ResponseClass> login(EmployeeDto employeeDto) throws LoginFailedException;


    ResponseEntity<ResponseClass> registeration(EmployeeDto employeeDto,HttpServletRequest request) throws DataAlreadyExistsException;  // by default the access specifier is public abstract for interface

    ResponseEntity<ResponseClass> signUpMasterUser(EmployeeDto employeeDto, HttpServletRequest request) throws DataAlreadyExistsException;
}
