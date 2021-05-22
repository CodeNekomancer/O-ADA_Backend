package com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.UserRole;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class getADAccOutputDTO {
    private String adacc_ID;
    private String username;
    private String password;
    private String email;
    private Set<UserRole> roles;

    public getADAccOutputDTO(ADAcc ada) {
        this.adacc_ID = ada.getAdacc_ID();
        this.username = ada.getUsername();
        this.password = ada.getPassword();
        this.email = ada.getEmail();
        this.email = ada.getEmail();
        this.roles = ada.getRoles();
    }
}
