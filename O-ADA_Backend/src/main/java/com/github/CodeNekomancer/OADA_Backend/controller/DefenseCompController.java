package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.persistence.service.DefenseCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("defensecomp")
public class DefenseCompController {

    @Autowired
    private DefenseCompService DefenseCompSrvc;
}
