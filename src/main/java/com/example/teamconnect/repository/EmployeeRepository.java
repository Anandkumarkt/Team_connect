package com.example.teamconnect.repository;

import com.example.teamconnect.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
repo is interface and JPArepo is also interface then extends is used.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


    Employee findByEmpIdAndPassword(int empId, String password);


    Employee findByPhoneNumber(long phoneNumber);


    Employee findByEmpIdOrEmailAndPassword(int empId, String email, String password);

    Employee findByEmail(String username);
}
