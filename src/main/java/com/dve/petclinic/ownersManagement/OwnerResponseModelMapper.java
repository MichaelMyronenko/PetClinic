package com.dve.petclinic.ownersManagement;

import com.dve.petclinic.entities.owner.Owner;

public class OwnerResponseModelMapper {

    public OwnerResponseModel mapToModel(Owner entity) {
        return ImmutableOwnerResponseModel.builder()
                .ownerId(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .username(entity.getUser().getUsername())
                .userId(entity.getUser().getId())
                .build();
    }
}
