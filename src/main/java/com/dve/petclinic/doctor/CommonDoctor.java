package com.dve.petclinic.doctor;

import com.dve.petclinic.user.CommonUser;
import lombok.*;

import javax.persistence.*;

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
}