package com.dve.petclinic.security.registration.ownerRegistration;

import com.dve.petclinic.owner.CommonOwner;
import com.dve.petclinic.owner.OwnerRepository;
import com.dve.petclinic.user.CommonUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerRegistrationServiceImpl implements OwnerRegistrationService{
    private final OwnerRepository ownerRepository;

    @Override
    public void registerOwner(CommonUser user) {
        CommonOwner owner = CommonOwner.builder().user(user).build();
        ownerRepository.save(owner);
    }
}
