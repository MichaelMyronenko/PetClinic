package com.dve.petclinic.owner;

import com.dve.petclinic.user.CommonUser;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@Table(name = "owners")
@AllArgsConstructor
@NoArgsConstructor
public class CommonOwner implements Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CommonUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonOwner that = (CommonOwner) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}