package com.example.teamconnect.response;

import com.example.teamconnect.dto.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseClass {

    private HttpStatus code;
    private String message;
    private Object data;


    public ResponseClass(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
