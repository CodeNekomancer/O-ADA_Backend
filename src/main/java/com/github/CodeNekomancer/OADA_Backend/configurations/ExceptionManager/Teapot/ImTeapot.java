package com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.Teapot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
public class ImTeapot extends RuntimeException {
    public ImTeapot() { super("I'm a teapot.");
    }
}