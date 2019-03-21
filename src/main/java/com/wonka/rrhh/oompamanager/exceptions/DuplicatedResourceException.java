package com.wonka.rrhh.oompamanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedResourceException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Resource '%s' already exists with id '%s'";

    private String resourceName;

    private long id;

    public DuplicatedResourceException(String resourceName, long id) {

        super(String.format(ERROR_MESSAGE, resourceName, id));
        this.resourceName = resourceName;
        this.id = id;
    }

    public String getResourceName() {

        return resourceName;
    }

    public void setResourceName(String resourceName) {

        this.resourceName = resourceName;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }
}
