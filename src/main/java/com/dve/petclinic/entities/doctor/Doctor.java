package com.dve.petclinic.entities.doctor;

import com.dve.petclinic.entities.user.CommonUser;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doctors")
public class Doctor {

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
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Doctor() {
    }

    public Doctor(CommonUser user) {
        this.user = user;
    }

    public Doctor(Long id, String position, CommonUser user) {
        this.id = id;
        this.position = position;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public CommonUser getUser() {
        return user;
    }

    public void setUser(CommonUser user) {
        this.user = user;
    }
}