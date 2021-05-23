package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.persistence.service.UniverseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("universe")
public class UniverseController {

    @Autowired
    private UniverseService UniverseSrvc;

    @ApiOperation(value = "Creates a Universe")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PostMapping("/gen")
    public ResponseEntity<?> genUniverse(@RequestBody String serverId) {
        return ResponseEntity.status(200).body(UniverseSrvc.genUniverse(serverId));
    }

    @ApiOperation(value = "Gets the universe list")
    @ApiResponse(code = 200, message = "", response = Pageable.class)
    @GetMapping("/get/pag")
    public ResponseEntity<?> getUnviersePag(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ResponseEntity.status(200).body(UniverseSrvc.getUniversePagSrvc(pageable));
    }

    @ApiOperation(value = "Gets a single universe")
    @ApiResponse(code = 200, message = "", response = Pageable.class)
    @GetMapping("/get/sng/{id}")
    public ResponseEntity<?> getUnvierseSng(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(UniverseSrvc.getUniverseSngSrvc(id));
    }

    @ApiOperation(value = "Modifies a Universe")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PutMapping("/mod/{id}")
    public ResponseEntity<?> modUnvierse(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(UniverseSrvc.modUniverseSrvc(id));
    }

    @ApiOperation(value = "Deletes a Universe")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> delUnvierse(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(UniverseSrvc.delUnvierseSrvc(id));
    }
}
