package com.dve.petclinic.doctor;

import com.dve.petclinic.user.CommonUser;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "doctors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonDoctor implements Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CommonUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonDoctor doctor = (CommonDoctor) o;
        return id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}