package com.dve.petclinic.ownersManagement;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OwnerFetcher {

    private final OwnerRepository ownerRepository;

    public OwnerFetcher(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner fetchOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new NotFoundException(
                "NotFound.OwnerFetcher.fetchOwnerById",
                "Not found owner by id: " + ownerId,
                null
        ));
    }

    public Owner fetchOwnerByUserId(Long userId) {
        return ownerRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(
                "NotFound.OwnerFetcher.fetchOwnerByUserId",
                "",
                null
        ));
    }
}
