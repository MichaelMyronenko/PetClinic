package com.dve.petclinic.petsManagement.creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetCreationModel {
    @NotBlank
    @Size(min = 2, max = 40)
    String name;
}
