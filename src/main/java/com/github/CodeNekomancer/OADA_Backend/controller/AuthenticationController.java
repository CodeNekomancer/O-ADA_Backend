package com.github.CodeNekomancer.OADA_Backend.controller;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.jwt.JwtTokenProvider;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.JwtUserResponse;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.LoginRequest;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.AddADAccInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/adacc/login")
    public JwtUserResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()

                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ADAcc user = (ADAcc) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);
        return convertUserEntityAndTokenToJwtUserResponse(user, jwtToken);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/me")
    public AddADAccInputDTO me(@AuthenticationPrincipal AddADAccInputDTO user) {
        return user;
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(ADAcc user, String jwtToken) {
        return JwtUserResponse
                .jwtUserResponseBuilder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .token(jwtToken)
                .build();
    }
}
