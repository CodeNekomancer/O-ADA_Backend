package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.EPService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ep")
public class EPController {

    @Autowired
    private EPService EPSrvc;

    @ApiOperation(value = "Creates an EP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/addEP")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> addEP(@RequestBody UAcc uAcc) {
        return new ResponseEntity(EPSrvc.addEPSrvc(uAcc), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes an EP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @DeleteMapping("/delEP")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> delEP(@RequestBody Long id) {
        return new ResponseEntity(EPSrvc.delEPSrvc(id), HttpStatus.OK);
    }
}
