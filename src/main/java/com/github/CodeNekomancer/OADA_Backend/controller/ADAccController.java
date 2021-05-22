package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.AddADAccInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.getADAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ADAccService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("adacc")
public class ADAccController {
    private ADAccService ADAccSrvc;

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

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADA')")
    public ResponseEntity<Iterable<ADAcc>> getADAccAll(Principal principal) {
        System.out.println(principal.getName());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ADAccSrvc.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/sng/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> getADAccSng(@PathVariable(name = "id") String id, Principal principal) {

        try {
            ADAcc a = this.ADAccSrvc.getUserMonoSrvc(id);
            return (!a.getAdacc_ID().isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(a) : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> delADAcc(@PathVariable(name = "id") String adacc_ID) {
        try {
            return (this.ADAccSrvc.delADAccSrvc(adacc_ID)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('LOG', 'ADA')")
    public ResponseEntity<?> modADAcc(@RequestBody() AddADAccInputDTO userEntity, @PathVariable(name = "id") String id) {
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
