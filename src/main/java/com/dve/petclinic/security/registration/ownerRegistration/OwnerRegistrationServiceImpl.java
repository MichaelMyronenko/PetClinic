package com.dve.petclinic.security.registration.ownerRegistration;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.owner.OwnerRepository;
import com.dve.petclinic.entities.user.CommonUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerRegistrationServiceImpl implements OwnerRegistrationService{
    private final OwnerRepository ownerRepository;

    @Override
    public void registerOwner(CommonUser user) {
        Owner owner = Owner.builder().user(user).build();
        ownerRepository.save(owner);
    }
}
