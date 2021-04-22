package com.dve.petclinic.doctorsManagement.reading;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
public abstract class DoctorResponseModel {
    public abstract Long getId();

    public abstract String getUsername();

    @Nullable
    public abstract String getPosition();
}
