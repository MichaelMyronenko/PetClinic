package com.dve.petclinic.entities.issue;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;
import lombok.*;

import javax.persistence.*;

@NamedEntityGraph(
        name = "issues,owners",
        attributeNodes = @NamedAttributeNode("createdBy")
)
@Entity
@Table(name = "issues")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name ="pet_id", referencedColumnName = "id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner createdBy;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor assignedTo;
}
