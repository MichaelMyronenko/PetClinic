package com.dve.petclinic.petsManagement.reading;

import org.immutables.value.Value;

@Value.Immutable
public abstract class PetResponseModel {

    public abstract Long getPetId();

    public abstract String getPetName();
}
