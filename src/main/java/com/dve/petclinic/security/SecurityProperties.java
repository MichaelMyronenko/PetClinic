package com.dve.petclinic.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "application.security")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class SecurityProperties {
    private final String secretDoctorsKey;
}