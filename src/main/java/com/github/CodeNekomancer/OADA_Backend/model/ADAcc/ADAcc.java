package com.github.CodeNekomancer.OADA_Backend.model.ADAcc;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.AuthManagement.UserRole;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ADAcc implements UserDetails {

    @Id
    private String adacc_ID;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    private String email;

    @OneToMany(mappedBy = "itsADAcc")
    private List<UAcc> universeAccounts;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles;

    private static final long serialVersionUID = -8596969777924519849L;

    public ADAcc(String username, Set<UserRole> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> result = new HashSet<>();
        for (UserRole userRole : roles) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + userRole.name());
            result.add(sga);
        }
        return result;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}