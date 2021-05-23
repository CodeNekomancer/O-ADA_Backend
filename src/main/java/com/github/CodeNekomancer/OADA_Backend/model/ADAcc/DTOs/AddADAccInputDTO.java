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
    private String email;

    public ADAcc converter(AddADAccInputDTO inputDTO) {
        ADAcc ada = new ADAcc();

        if (inputDTO.getUsername() != null) ada.setUsername(inputDTO.getUsername());
        if (inputDTO.getPassword() != null) ada.setPassword(inputDTO.getPassword());
        if (inputDTO.getEmail() != null) ada.setEmail(inputDTO.getEmail());

        return ada;
    }

}
