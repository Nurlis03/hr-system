package org.example.hrsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.hrsystem.entity.base.BaseEntity;
import org.example.hrsystem.entity.base.SoftDeletable;
import org.example.hrsystem.enums.Role;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@Table(name = "user", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Where(clause = "deleted_at IS NULL")
public class User extends SoftDeletable implements UserDetails {

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime lockExpirationTime;

    @OneToMany
    private List<VacancyComment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lockExpirationTime == null || lockExpirationTime.isBefore(LocalDateTime.now());
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
