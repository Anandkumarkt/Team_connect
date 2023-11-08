package com.example.teamconnect.exception;

import com.example.teamconnect.response.ResponseClass;
import com.example.teamconnect.response.StringConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // annotation to tell the compiler that it is an exception handler class
public class ExceptionHandlerClass {

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ResponseClass> signUpErrorResponse(DataAlreadyExistsException dataAlreadyExistsException){

        ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST, dataAlreadyExistsException.getMessage());

        return new ResponseEntity<ResponseClass>(responseClass,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseClass loginErrorResponse(LoginFailedException loginFailedException){
        ResponseClass responseClass = new ResponseClass(HttpStatus.BAD_REQUEST,StringConstants.LOGIN_FAILED);
        return  responseClass;
    }
}
