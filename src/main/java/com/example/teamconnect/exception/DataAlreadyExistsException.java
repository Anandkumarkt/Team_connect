package com.example.teamconnect.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component // this is the base for @Service,@Controller ,@Repository this is a response class so we giving @component as common
@Getter
@Setter
public class DataAlreadyExistsException extends Exception {
    private String message;

    public DataAlreadyExistsException(String message) {
        this.message = message;

    }

    public DataAlreadyExistsException() {
    }
}
