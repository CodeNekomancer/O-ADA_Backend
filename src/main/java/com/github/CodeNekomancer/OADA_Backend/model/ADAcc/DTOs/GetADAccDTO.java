package com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetADAccDTO {

    private String username;
    private Set<String> roles;

}
