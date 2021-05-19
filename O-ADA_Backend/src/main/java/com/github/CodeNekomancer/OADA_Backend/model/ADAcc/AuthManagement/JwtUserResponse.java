package com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends ADAcc {

    private String token;

    //indicaremos otro nombre al builder
    @Builder(builderMethodName="jwtUserResponseBuilder")
    //los parametros dependeran de lo que contenga el UserEntity
    public JwtUserResponse(String username, String email, Set<UserRole> roles, String token) {
        super(username,roles);
        this.token = token;
    }
}
