package com.dve.petclinic.entities.pet;

import com.dve.petclinic.entities.owner.Owner;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;
}
