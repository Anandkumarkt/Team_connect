package com.example.teamconnect.dao;

import com.example.teamconnect.dto.EmployeeDto;
import com.example.teamconnect.entity.Employee;
import com.example.teamconnect.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;
    private EmployeeDto employeeDto = new EmployeeDto() ;
    private Employee employee = new Employee();
   
  



    public EmployeeDto findEmployeeDetails(int empId, String password) {
        Employee employee = employeeRepository.findByEmpIdAndPassword(empId,password);
        return employeeDto.convertToDto(employee);

    }


    public EmployeeDto findPhoneNumber(long phoneNumber) {
         Employee employee = employeeRepository.findByPhoneNumber(phoneNumber);
        EmployeeDto empDto = employeeDto.convertToDto(employee);
        return empDto;
    }

    public EmployeeDto saveUser(EmployeeDto employeeDto) {

        Employee employee1= employee.convertToEntity(employeeDto);
        employee1 = employeeRepository.save(employee1);
        EmployeeDto employeeDto1 = employeeDto.convertToDto(employee1);
        return  employeeDto1;
    }
}
