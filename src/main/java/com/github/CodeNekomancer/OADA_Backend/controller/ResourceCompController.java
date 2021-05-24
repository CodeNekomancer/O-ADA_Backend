package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.EPService;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.ResourceCompService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ep")
public class ResourceCompController {
    @Autowired
    private ResourceCompService resourceCompService;


}
