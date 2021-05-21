package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.UAccService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("uacc")
public class UAccController {

    @Autowired
    private UAccService UAccSrvc;

    @ApiOperation(value = "Creates a UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/genUAcc")
    public ResponseEntity<?> genUAcc(@RequestBody UAccInputDTO UAccDTO) {
        return new ResponseEntity(UAccSrvc.genUAccSrvc(UAccDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets an UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @GetMapping("/getUAccMono")
    public ResponseEntity<?> getUAccMono(@RequestParam UAcc uacc) {
        return new ResponseEntity(UAccSrvc.getUAccMonoSrvc(uacc.getUacc_ID()), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes an UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @DeleteMapping("/delUAccMono")
    public ResponseEntity<?> delUAccMono(@RequestBody Long id) {
        return new ResponseEntity(UAccSrvc.delUAccMonoSrvc(id), HttpStatus.OK);
    }

}
