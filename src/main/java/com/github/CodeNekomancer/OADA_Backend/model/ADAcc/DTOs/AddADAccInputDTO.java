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
public class AddADAccInputDTO {
    private String username;
    private String password;
    private Set<UserRole> roles;

    public ADAcc converter(AddADAccInputDTO inputDTO) {
        ADAcc ada = new ADAcc();
        ada.setUsername(inputDTO.getUsername());
        ada.setPassword(inputDTO.getPassword());
        ada.setRoles(inputDTO.getRoles());

        return ada;
    }

}
