package com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ADAccNotFoundException extends RuntimeException {
    public ADAccNotFoundException() {
        super("The ADAcc does not exist.");
    }
}
