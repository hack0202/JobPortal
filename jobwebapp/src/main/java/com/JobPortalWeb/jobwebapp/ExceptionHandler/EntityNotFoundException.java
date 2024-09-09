package com.JobPortalWeb.jobwebapp.ExceptionHandler;

public class EntityNotFoundException extends RuntimeException {
	private String entityName;
    private String fieldName;
    private Object fieldValue;

    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
