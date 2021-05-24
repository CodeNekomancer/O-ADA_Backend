package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.IAuthenticationFacade;
import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ExpeditionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("expedition")
public class ExpeditionController {

    @Autowired
    private ExpeditionService ExpeditionSrvc;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @ApiOperation(value = "Creates an Expedition")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/genExpedition")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> genExpedition(@RequestBody String data) {
        return ResponseEntity.ok().body(ExpeditionSrvc.genExpeditionSrvc(data, authenticationFacade.getAuthentication().getName()));
    }

    @ApiOperation(value = "Gets its own expeditions from the expedition profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/get/all")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> getAll(@RequestBody Long id) {
        return ResponseEntity.ok().body(ExpeditionSrvc.getAllExpeditionSrvc(id, authenticationFacade.getAuthentication().getName()));
    }
}
