package com.example.teamconnect.controller;

import com.example.teamconnect.dto.EmployeeDto;
import com.example.teamconnect.exception.DataAlreadyExistsException;
import com.example.teamconnect.exception.LoginFailedException;
import com.example.teamconnect.response.ResponseClass;
import com.example.teamconnect.service.EmployeeService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    /*@PostMapping("/signUp")
    public ResponseEntity<ResponseClass> signUp(@RequestBody EmployeeDto employeeDto,HttpServletRequest request) throws DataAlreadyExistsException {
        return employeeService.registeration(employeeDto,request);

    }*/

    @PostMapping("/signUp")
    public int signUp(@RequestBody EmployeeDto employeeDto,HttpServletRequest request) throws DataAlreadyExistsException {
        return 10;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseClass> login(@RequestBody EmployeeDto employeeDto){
        return employeeService.login(employeeDto);
    }

    @PostMapping("/masterUser")
    public ResponseEntity<ResponseClass> masterUserSignup(@RequestBody EmployeeDto employeeDto, HttpServletRequest request) throws DataAlreadyExistsException {
        return employeeService.signUpMasterUser(employeeDto,request);
    }

}
