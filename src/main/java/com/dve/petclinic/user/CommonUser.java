package com.dve.petclinic.user;

import com.dve.petclinic.user.role.CommonRole;
import com.dve.petclinic.user.role.RoleName;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NamedEntityGraph(
        name = "user.roles",
        attributeNodes = @NamedAttributeNode("roles")
)
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class CommonUser implements User {

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

    @Override
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
}

