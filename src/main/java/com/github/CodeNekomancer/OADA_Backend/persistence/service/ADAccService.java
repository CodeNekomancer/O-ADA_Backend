package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.UserRole;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ADAccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class ADAccService extends BaseService<ADAcc, Long, ADAccRepository> {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Optional<ADAcc> findByUserName(String username) {
        return this.repo.findByUsername(username);
    }

    public ADAcc addADAccSrvc(ADAcc userEntity) {
        Set<UserRole> defaultRoles = new HashSet<UserRole>();
        userEntity.setAdacc_ID((long) Math.abs(new Random().nextInt()));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        if (userEntity.getRoles() == null) {
            defaultRoles.add(UserRole.USER);
            userEntity.setRoles(defaultRoles);
        } else if (userEntity.getRoles().size() == 0) {
            defaultRoles.add(UserRole.USER);
            userEntity.setRoles(defaultRoles);
        }
        return this.repo.save(userEntity);
    }

    public ADAcc getUserMonoSrvc(Long id) {
        Optional<ADAcc> opUsr = this.repo.findById(id);
        return (opUsr.isPresent()) ? opUsr.get() : new ADAcc();
    }

    public Boolean delADAccSrvc(Long id) {
        boolean flag = false;

        if (this.ADAccExists(id)) {
            this.deleteById(id);
            flag = true;
        }
        return flag;
    }

    public Boolean modADAccSrvc(ADAcc user, Long idUsuario) {
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

    private Boolean ADAccExists(Long id) {
        Optional<ADAcc> opAl = this.repo.findById(id);
        return opAl.isPresent();
    }
}
