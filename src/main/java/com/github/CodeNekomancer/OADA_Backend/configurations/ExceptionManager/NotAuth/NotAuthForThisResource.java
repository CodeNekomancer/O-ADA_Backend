package com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotAuth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NotAuthForThisResource extends RuntimeException {
    public NotAuthForThisResource() {
        super("You souldn't be trying to retrieve this data.");
    }
}
