package com.example.teamconnect.entity;

import com.example.teamconnect.dto.EmployeeDto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter

//@AllArgsConstructor // used to create a constrcutor with all args
//@RequiredArgsConstructor // used to create a constrcutor with required args
@Entity  // insisting JPA that this table deals with DB
@ToString // if the value we get in object then to string will convert that to string
@Table(name= "employee") // eventhough we give in caps in db it takes the name in small letters
public class Employee {
    @Id
    private int empId;
    private String firstName;
    private String lastName;

    private long phoneNumber;
    private String address;
    private String password;

    private String email;
    private String role;

    public Employee convertToEntity(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.empId = employeeDto.getEmpId();
        employee.firstName = employeeDto.getFirstName();
        employee.lastName = employeeDto.getLastName();
        employee.phoneNumber = employeeDto.getPhoneNumber();
        employee.address = employeeDto.getAddress();
        employee.password = employeeDto.getPassword();
        employee.email=employeeDto.getEmail();
        employee.role= employeeDto.getRole();
        return employee;
    }


}
