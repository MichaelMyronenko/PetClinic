package com.dve.petclinic.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "application.security")
@ConstructorBinding
public class SecurityProperties {

    private final String secretDoctorsKey;

    public SecurityProperties(String secretDoctorsKey) {
        this.secretDoctorsKey = secretDoctorsKey;
    }

    public String getSecretDoctorsKey() {
        return secretDoctorsKey;
    }
}