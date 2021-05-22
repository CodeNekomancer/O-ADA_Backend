package com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ADAccDTOConverter {
    @Autowired
    private final ModelMapper modelMapper;

    public ADAccDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AddADAccInputDTO convertUserEntityToGetUserDto(ADAcc user) {
        return modelMapper.map(user, AddADAccInputDTO.class);
    }
}
