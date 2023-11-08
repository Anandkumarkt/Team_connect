package com.example.teamconnect.dto;

import com.example.teamconnect.entity.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDto {

    private int empId;
    private String firstName;
    private String lastName;

    private long phoneNumber;
    private String address;

    private String password;
    private String email;
    private String role;


    public EmployeeDto convertToDto(Employee employee){
        EmployeeDto employeeDto = null;
        if(employee != null) {
            employeeDto = new EmployeeDto();
            employeeDto.setEmpId(employee.getEmpId());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setPhoneNumber(employee.getPhoneNumber());
            employeeDto.setAddress(employee.getAddress());
            employeeDto.setPassword(employee.getPassword());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setRole(employee.getRole());
        }
        return employeeDto;
    }

}
