package com.dve.petclinic.entities.user;

import com.dve.petclinic.entities.user.role.CommonRole;
import com.dve.petclinic.entities.user.role.RoleName;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraph(
        name = "user.roles",
        attributeNodes = @NamedAttributeNode("roles")
)
@Entity
@Table(name = "users")
public class CommonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<CommonRole> roles;

    private boolean active;

    public boolean hasRole(RoleName roleName) {
        return roles.stream()
                .anyMatch(commonRole -> commonRole.getRoleName().equals(roleName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUser that = (CommonUser) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public CommonUser() {
    }

    public CommonUser(String username, String password, Set<CommonRole> roles, boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public CommonUser(Long id, String username, String password, Set<CommonRole> roles, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CommonRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CommonRole> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

