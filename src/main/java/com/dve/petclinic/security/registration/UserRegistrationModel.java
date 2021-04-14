package com.dve.petclinic.security.registration;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {
    @NotBlank
    @Size(min = 3, max = 20)
    public String username;
    @NotBlank
    @Size(min = 4, max = 18)
    public String password;
    public String secretDoctorsKey;
}
