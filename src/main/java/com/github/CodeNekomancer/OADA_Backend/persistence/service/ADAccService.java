package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.UserRole;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.getADAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ADAccRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class ADAccService extends BaseService<ADAcc, String, ADAccRepository> {

    @Lazy
    private final PasswordEncoder passwordEncoder;

    public Optional<ADAcc> findByUserName(String username) {
        return this.repo.findByUsername(username);
    }

    public getADAccOutputDTO addADAccSrvc(ADAcc userEntity) {
        Set<UserRole> defaultRoles = new HashSet<>();
        userEntity.setAdacc_ID(String.valueOf(Math.abs(new Random().nextInt())));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        if (userEntity.getRoles() == null) {
            defaultRoles.add(UserRole.LOG);
            userEntity.setRoles(defaultRoles);
        } else if (userEntity.getRoles().size() == 0) {
            defaultRoles.add(UserRole.LOG);
            userEntity.setRoles(defaultRoles);
        }
        return new getADAccOutputDTO(this.repo.save(userEntity));
    }

    public ADAcc getUserSngSrvc(String id) {
        Optional<ADAcc> opUsr = this.repo.findById(id);
        return opUsr.orElseGet(ADAcc::new);
    }

    public Boolean delADAccSrvc(String id) {
        boolean flag = false;

        if (this.ADAccExists(id)) {
            this.deleteById(id);
            flag = true;
        }
        return flag;
    }

    public Boolean modADAccSrvc(ADAcc user, String idUsuario) {
        boolean flag = false;
        if (this.ADAccExists(idUsuario)) {
            Optional<ADAcc> a = this.repo.findById(idUsuario);
            user.setAdacc_ID(idUsuario);
            if (user.getPassword() == null) {
                user.setPassword(a.get().getPassword());
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getRoles() == null || user.getRoles().size() == 0) user.setRoles(a.get().getRoles());
            if (user.getUsername() == null) user.setUsername(a.get().getUsername());
            this.repo.save(user);

            flag = true;
        }
        return flag;
    }

    private Boolean ADAccExists(String id) {
        Optional<ADAcc> opAl = this.repo.findById(id);
        return opAl.isPresent();
    }
}
