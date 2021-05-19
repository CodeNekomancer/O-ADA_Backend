package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.CelestialBodyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("celestialbody")
public class CelestialBodyController {

    @Autowired
    private CelestialBodyService CelestialBodySrvc;

    @ApiOperation(value = "Creates an Celestial Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/genCelestialBodySchemeAuto")
    public ResponseEntity<?> genCelestialBodySchemeAuto(@RequestBody UAcc uacc) {
        return new ResponseEntity(CelestialBodySrvc.genCelestialBodySchemeAuto(uacc), HttpStatus.OK);
    }
}
