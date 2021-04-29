package com.dve.petclinic.security.registration.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Password annotation;

    @Override
    public void initialize(Password constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        String constraints = getConstraints(annotation);
        Pattern passwordPattern = Pattern.compile(constraints);

        return passwordPattern.matcher(password).matches();
    }

    private String getConstraints(Password annotation) {
        return "^" +
                getDigitsRequirement(annotation.digits()) +
                getLowercaseRequirement(annotation.lowercase()) +
                getUppercaseRequirement(annotation.uppercase()) +
                getSpecialCharsRequirement(annotation.specialChars()) +
                "." +
                getSizeRequirement(annotation.min(), annotation.max()) +
                "$";
    }

    private String getSizeRequirement(int min, int max) {
        return "{" + min + "," + max + "}";
    }

    private String getDigitsRequirement(ValidationOptions option) {
        switch (option) {
            case REQUIRED:
                return "(?=.*[0-9])";
            case DISALLOWED:
                return "(?!.*[0-9])";
            default:
                return "";
        }
    }

    private String getLowercaseRequirement(ValidationOptions option) {
        switch (option) {
            case REQUIRED:
                return "(?=.*[a-z])";
            case DISALLOWED:
                return "(?!.*[a-z])";
            default:
                return "";
        }
    }

    private String getUppercaseRequirement(ValidationOptions option) {
        switch (option) {
            case REQUIRED:
                return "(?=.*[A-Z])";
            case DISALLOWED:
                return "(?!.*[A-Z])";
            default:
                return "";
        }
    }

    private String getSpecialCharsRequirement(ValidationOptions option) {
        switch (option) {
            case REQUIRED:
                return "(?=.*[\\!\\@\\#\\&\\(\\)\\–\\[\\{\\}\\]\\:\\;\\'\\,\\?\\/\\*\\~\\$\\^\\+\\=\\<\\>])";
            case DISALLOWED:
                return "(?!.*[\\!\\@\\#\\&\\(\\)\\–\\[\\{\\}\\]\\:\\;\\'\\,\\?\\/\\*\\~\\$\\^\\+\\=\\<\\>])";
            default:
                return "";
        }
    }
}
