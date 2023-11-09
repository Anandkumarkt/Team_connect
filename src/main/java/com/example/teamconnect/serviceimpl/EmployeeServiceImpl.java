package com.example.teamconnect.serviceimpl;

import com.example.teamconnect.dao.EmployeeDao;
import com.example.teamconnect.dto.EmployeeDto;
import com.example.teamconnect.entity.Employee;
import com.example.teamconnect.exception.DataAlreadyExistsException;
import com.example.teamconnect.exception.LoginFailedException;
import com.example.teamconnect.repository.EmployeeRepository;
import com.example.teamconnect.response.ResponseClass;
import com.example.teamconnect.response.StringConstants;
import com.example.teamconnect.service.EmployeeService;
import com.example.teamconnect.service.MailService;
import com.example.teamconnect.utils.JwtUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EmployeeDao employeeDao;

    private String masterToken = "eeeeeeeeeeqqqqqqaaaaakdjkhdskjsjkhdkjshdkhskhksjdkjdcsbshxQHWWHKJWDKJBSCHAV";

    @Override
    public ResponseEntity<ResponseClass> registeration(EmployeeDto employeeDto,HttpServletRequest request) throws DataAlreadyExistsException{
       /* Employee employee = new Employee();
        employee = employee.convertToEntity(employeeDto);*/

         /*
        if we are using findById(default queries ) then it ask us to change the return type as optional<>. So change it to .get() at end.
        Employee empId = employeeRepository.findById(employeeDto.getEmpId()).get();

                      OR

        Optional<Employee> employee1 = employeeRepository.findById(employee.getEmpId());

         */

        // EmployeeDto emp= employeeDao.findEmployeeDetails(employeeDto.getEmpId(),employeeDto.getPassword());

        EmployeeDto employeeDto1 = employeeDao.findPhoneNumber(employeeDto.getPhoneNumber());
        if (employeeDto1.getRole().equals("master")) {
            if (null != employeeDto1) {

                if (employeeDto1.getEmail().equals(employeeDto.getEmail())) {
                    throw new DataAlreadyExistsException(StringConstants.EMAIL_EXISTS);

                } else if (employeeDto1.getPhoneNumber() == employeeDto.getPhoneNumber()) {
                    throw new DataAlreadyExistsException(StringConstants.PHONENUMBER_EXISTS);

                }

                /*if(emp != null){
                 *//*System.err.println("The employee already exists");
            ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, StringConstants.USER_EXISTS,null);
            return responseClass;*//*
            throw new DataAlreadyExistsException();
        }*/
            }

            employeeDto1 = employeeDao.saveUser(employeeDto);
            //mailService.sendEmailToEmployee(employeeDto.getEmail(),String subject, String message);
            mailService.sendEmailToEmployee(employeeDto1.getEmail(), StringConstants.MAIL_SUBJECT, "Hi " + employeeDto1.getFirstName() + "\n" + StringConstants.MAIL_MESSAGE);
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.SIGN_UP_SUCCESS, employeeDto1);
            return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);
        }
        return null;
    }

    @Override
    public ResponseEntity<ResponseClass> login(EmployeeDto employeeDto){
        Employee employee = employeeRepository.findByEmpIdOrEmailAndPassword(employeeDto.getEmpId(), employeeDto.getEmail(), employeeDto.getPassword());
        if (employee != null) {
            EmployeeDto employeeDto1 = employeeDto.convertToDto(employee);
            String token = jwtUtils.generateJWT(employeeDto1);
            Map<String, Object> generatedToken = new HashMap<>();
            generatedToken.put("accessToken", token);
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.LOGIN_SUCCESS, generatedToken);
            return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);
        } 
        return null;
    }

    public ResponseEntity<ResponseClass> signUpMasterUser(EmployeeDto employeeDto, HttpServletRequest request) throws DataAlreadyExistsException {
        String master = request.getHeader("MasterCode");
        int add=0;
        if (master.equals(masterToken)) {
            List<Employee> allEmployee = employeeRepository.findAll();
            if (allEmployee.isEmpty()) {
                Employee employee = new Employee().convertToEntity(employeeDto);
                employee.setRole("master");
                employee = employeeRepository.save(employee);
                EmployeeDto employeeDto1 = new EmployeeDto().convertToDto(employee);
                ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.SIGN_UP_SUCCESS, employeeDto1);
                return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);
            } 

        }
        return null;
    }
}



