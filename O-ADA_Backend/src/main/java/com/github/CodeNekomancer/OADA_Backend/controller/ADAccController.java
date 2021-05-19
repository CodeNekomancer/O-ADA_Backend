package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ADAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("adacc")
public class ADAccController {
    @Autowired
    private ADAccService ADAccSrvc;

    @PostMapping("/add")
    public ResponseEntity<ADAcc> genADAcc(@RequestBody ADAcc newUser) {
        try {
            if (!ADAccSrvc.findByUserName(newUser.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(ADAccSrvc.genADAccSrvc(newUser));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<ADAcc>> getADAccMult() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ADAccSrvc.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("{idADAcc}")
    public ResponseEntity<?> getADAccMono(@PathVariable(name = "id") Long adacc_ID) {
        try {
            ADAcc a = this.ADAccSrvc.getUserMonoSrvc(adacc_ID);
            return (!a.getAdacc_ID().toString().isEmpty()) ? ResponseEntity.status(HttpStatus.OK).body(a) : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{idADAcc}")
    public ResponseEntity<?> delADAcc(@PathVariable(name = "id") Long adacc_ID) {
        try {
            return (this.ADAccSrvc.delADAccSrvc(adacc_ID)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{idADAcc}/edit")
    public ResponseEntity<?> modADAcc(@RequestBody(required = true) ADAcc userEntity, @PathVariable Long id) {
        try {
            return (this.ADAccSrvc.modADAccSrvc(userEntity, id))
                    ? ResponseEntity.ok().body(this.ADAccSrvc.findById(id))
                    : ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
