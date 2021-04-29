package com.dve.petclinic.generalExceptions;

public class NotFoundException extends RuntimeException {

    private final Object[] args;
    private final String messageCode;
    private final String details;

    public NotFoundException(String messageCode, String details, Object[] args) {
        super(details);
        this.args = args;
        this.messageCode = messageCode;
        this.details = details;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public String getDetails() {
        return details;
    }
}
