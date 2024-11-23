package in.devstream.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "users",
        indexes = {
            @Index(unique = true, columnList = "email")},
        uniqueConstraints = {
            @UniqueConstraint(name = "UniqueEmail", columnNames = {"email"})})
public class User implements UserDetails {

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private String fullname;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private boolean enabled;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    // https://stackoverflow.com/questions/42334475/spring-jpa-users-roles-authentication-how-do-i-avoid-duplicate-role-entries
    @OneToMany(mappedBy = "role", cascade = CascadeType.DETACH, orphanRemoval = false)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authoritiesSet = new HashSet<>();

        for (Role role : this.roles) {
            authoritiesSet.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> authoritiesList = new ArrayList<>(authoritiesSet);
        return authoritiesList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }
}
