package com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UAccNotFoundException extends RuntimeException {
    public UAccNotFoundException() {
        super("The UAcc does not exist.");
    }
}
