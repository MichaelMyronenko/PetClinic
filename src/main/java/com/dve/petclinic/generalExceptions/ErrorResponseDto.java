package com.dve.petclinic.generalExceptions;

import org.immutables.value.Value;

@Value.Immutable
public abstract class ErrorResponseDto {
    public abstract String message();
}
