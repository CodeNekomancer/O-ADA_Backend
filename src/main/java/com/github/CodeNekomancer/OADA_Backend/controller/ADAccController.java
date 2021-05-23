package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.IAuthenticationFacade;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.AddADAccInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.getADAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ADAccService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@RequestMapping("adacc")
public class ADAccController {
    private final ADAccService ADAccSrvc;
    private final IAuthenticationFacade authenticationFacade;

    @PostMapping("/add")
    public ResponseEntity<getADAccOutputDTO> addADAcc(@RequestBody AddADAccInputDTO newUser) {
        try {
            if (ADAccSrvc.findByUserName(newUser.getUsername()).isEmpty()) {
                AddADAccInputDTO dto = new AddADAccInputDTO();
                return ResponseEntity.status(HttpStatus.CREATED).body(ADAccSrvc.addADAccSrvc(dto.converter(newUser)));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAnyRole('ADA')")
    public ResponseEntity<Iterable<ADAcc>> getADAccAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ADAccSrvc.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get/sng/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> getADAccSng(@PathVariable(name = "id") String id) {
        getADAccOutputDTO ada = new getADAccOutputDTO(this.ADAccSrvc.getUserSngSrvc(id)); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        try {
            return (!ada.getAdacc_ID().isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(ada) : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> delADAcc(@PathVariable(name = "id") String id) {
        getADAccOutputDTO ada = new getADAccOutputDTO(this.ADAccSrvc.getUserSngSrvc(id)); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        try {
            return (this.ADAccSrvc.delADAccSrvc(id)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/mod/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> modADAcc(@RequestBody() AddADAccInputDTO userEntity, @PathVariable(name = "id") String id) {
        getADAccOutputDTO ada = new getADAccOutputDTO(this.ADAccSrvc.getUserSngSrvc(id)); // TODO: let admins override this
        if (!authenticationFacade.getAuthentication().getName().equals(ada.getUsername())) return ResponseEntity.status(401).body("You are trying to access to a resource that it is not on your domain. Think twice what you are asking for.");

        try {
            AddADAccInputDTO dto = new AddADAccInputDTO();
            return (this.ADAccSrvc.modADAccSrvc(dto.converter(userEntity), id))
                    ? ResponseEntity.ok().body(this.ADAccSrvc.findById(id))
                    : ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
