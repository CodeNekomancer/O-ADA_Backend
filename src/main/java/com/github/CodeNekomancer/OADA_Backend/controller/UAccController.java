package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.IAuthenticationFacade;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.getADAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ADAccService;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.UAccService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("uacc")
public class UAccController {
    private final UAccService UAccSrvc;
    private final ADAccService adAccService;
    private final IAuthenticationFacade authenticationFacade;

    @ApiOperation(value = "Creates a UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = UAccOutputDTO.class),
            @ApiResponse(code = 404, message = "")
    })
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> addUAcc(@RequestBody UAccInputDTO UAccDTO) {
        getADAccOutputDTO ada = new getADAccOutputDTO(adAccService.getUserSngSrvc(UAccDTO.getItsADAcc())); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        return ResponseEntity.status(200).body(UAccSrvc.addUAccSrvc(UAccDTO));
    }

    @ApiOperation(value = "Gets an UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = UAccOutputDTO.class),
            @ApiResponse(code = 404, message = "")
    })
    @GetMapping("/sng/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> getUAccSng(@PathVariable(name = "id") Long id) {
        if (this.UAccSrvc.findById(id).isEmpty()) return ResponseEntity.notFound().build();

        // TODO: let admins override this
        getADAccOutputDTO ada = new getADAccOutputDTO(adAccService.getUserSngSrvc(this.UAccSrvc.findById(id).get().getItsADAcc().getAdacc_ID())); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        return ResponseEntity.status(200).body(UAccSrvc.getUAccSngSrvc(id));
    }

    @ApiOperation(value = "Deletes an UAcc")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "")
    })
    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> delUAccSng(@PathVariable(name = "id") Long id) {
        if (this.UAccSrvc.findById(id).isEmpty()) return ResponseEntity.notFound().build();

        // TODO: let admins override this
        getADAccOutputDTO ada = new getADAccOutputDTO(adAccService.getUserSngSrvc(this.UAccSrvc.findById(id).get().getItsADAcc().getAdacc_ID())); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        return ResponseEntity.status(200).body(UAccSrvc.delUAccSngSrvc(id));
    }

}
