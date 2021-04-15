package com.dve.petclinic.owner;

import com.dve.petclinic.user.User;

public interface Owner {
    Long getId();
    String getPhoneNumber();
    User getUser();
}
