package com.dve.petclinic.security.registration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommonUserRegistrationModel {

    @NotBlank
    @Size(min = 3, max = 20)
    @Username
    private String username;

    @NotBlank
    @Size(min = 4, max = 18)
    private String password;

    private String secretDoctorsKey;

    public CommonUserRegistrationModel(String username, String password, String secretDoctorsKey) {
        this.username = username;
        this.password = password;
        this.secretDoctorsKey = secretDoctorsKey;
    }

    public CommonUserRegistrationModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretDoctorsKey() {
        return secretDoctorsKey;
    }

    public void setSecretDoctorsKey(String secretDoctorsKey) {
        this.secretDoctorsKey = secretDoctorsKey;
    }
}
