package com.dve.petclinic.doctor;

import com.dve.petclinic.user.User;

public interface Doctor {
    Long getId();
    User getUser();
    String getPosition();
}
