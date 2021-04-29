package com.dve.petclinic.ownersManagement;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
public abstract class OwnerResponseModel {

    public abstract Long getOwnerId();

    @Nullable
    public abstract String getPhoneNumber();

    public abstract Long getUserId();

    public abstract String getUsername();
}
