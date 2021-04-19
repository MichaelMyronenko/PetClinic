package com.dve.petclinic.petsManagement.update;

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
public class PetUpdateModel {
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;
}
