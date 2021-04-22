package com.dve.petclinic.security.registration;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.user.CommonUser;
import org.springframework.stereotype.Service;

@Service
public class OwnerRegistrationService {

    private final OwnerRepository ownerRepository;

    public OwnerRegistrationService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void registerOwner(CommonUser user) {
        Owner owner = new Owner(user);

        ownerRepository.save(owner);
    }
}
