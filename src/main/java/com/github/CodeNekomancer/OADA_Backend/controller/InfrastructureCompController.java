package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.persistence.service.InfrastructureCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("infrastructureComp")
public class InfrastructureCompController {

    @Autowired
    private InfrastructureCompService InfrastructureCompSrvc;
}
