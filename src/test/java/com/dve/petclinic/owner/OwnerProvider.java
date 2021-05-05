package com.dve.petclinic.owner;

import com.dve.petclinic.entities.owner.Owner;
import com.dve.petclinic.entities.user.CommonUser;
import com.dve.petclinic.security.CommonUserProvider;

import java.util.ArrayList;
import java.util.List;

public final class OwnerProvider {

    public static Owner getOwner() {
        return new Owner(CommonUserProvider.getCommonUser());
    }

    public static Owner getOwner(Long id) {
        return new Owner(id, "somephonenumber",CommonUserProvider.getCommonUser());
    }

    public static Owner getOwner(CommonUser user) {
        return new Owner("somephonenumber", user);
    }

    public static List<Owner> getOwners(int numOfOwners) {
        List<Owner> owners = new ArrayList<>();

        for (int i = 0; i < numOfOwners; i++) {
            owners.add(getOwner((long) i + 1));
        }

        return owners;
    }
}
