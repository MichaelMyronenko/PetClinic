package com.dve.petclinic.security.registration.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.dve.petclinic.security.registration.constraints.ValidationOptions.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{Password.user.password}";

    int min() default 1;

    int max() default 100;

    ValidationOptions digits() default ALLOWED;

    ValidationOptions lowercase() default REQUIRED;

    ValidationOptions uppercase() default ALLOWED;

    ValidationOptions specialChars() default DISALLOWED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
