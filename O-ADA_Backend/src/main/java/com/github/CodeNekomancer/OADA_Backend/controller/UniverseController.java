package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.UniverseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/genUniverse")
    public ResponseEntity<?> genUniverse(@RequestBody Universe uni) {
        return new ResponseEntity(UniverseSrvc.genUniverse(uni), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets the universe list")
    @ApiResponse(code = 200, message = "", response = Pageable.class)
    @GetMapping("/getUnvierseMult")
    public ResponseEntity<?> getUnvierseMult(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity(UniverseSrvc.getUniverseSrvc(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Modifies a Universe")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @PutMapping("/modUnvierse")
    public ResponseEntity<?> modUnvierse(@RequestBody Universe uni) {
        return new ResponseEntity(UniverseSrvc.modUniverseSrvc(uni), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a Universe")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = Boolean.class),
            @ApiResponse(code = 404, message = "", response = Boolean.class)
    })
    @DeleteMapping("/delUnvierse")
    public ResponseEntity<?> delUnvierse(@RequestBody Long id) {
        return new ResponseEntity(UniverseSrvc.delUnvierseSrvc(id), HttpStatus.OK);
    }
}
