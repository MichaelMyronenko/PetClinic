package com.dve.petclinic.security.registration.userRegistration;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserRegistrationModel implements UserRegistrationModel {

    @NotBlank
    @Size(min = 3, max = 20)
    @Username
    public String username;

    @NotBlank
    @Size(min = 4, max = 18)
    public String password;

    public String secretDoctorsKey;
}
