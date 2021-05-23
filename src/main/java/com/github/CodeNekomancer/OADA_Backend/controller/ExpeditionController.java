package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.IAuthenticationFacade;
import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ExpeditionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> addExpedition(@RequestBody String expedition) {
        return ResponseEntity.status(200).body(ExpeditionSrvc.addExpeditionSrvc(expedition));
    }

    @ApiOperation(value = "Gets the expeditions of an EP")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @GetMapping("/get/{EPName}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> getExpeditionByEP(@PathVariable(name = "EPName") String EPName) {
        return ResponseEntity.status(200).body(ExpeditionSrvc.getExpeditionByEPSrvc(EPName, authenticationFacade.getAuthentication().getName()));
    }
}
