package com.dve.petclinic.entities.issue;

import com.dve.petclinic.entities.doctor.Doctor;
import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.pet.Pet;

import javax.persistence.*;

@NamedEntityGraph(
        name = "issues,owners",
        attributeNodes = @NamedAttributeNode("createdBy")
)
@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner createdBy;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor assignedTo;

    public Issue() {
    }

    public Issue(Long id, String title, String description, IssueStatus status, Pet pet, Owner createdBy, Doctor assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.pet = pet;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }

    public Issue(String title, String description, IssueStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Owner createdBy) {
        this.createdBy = createdBy;
    }

    public Doctor getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Doctor assignedTo) {
        this.assignedTo = assignedTo;
    }
}
