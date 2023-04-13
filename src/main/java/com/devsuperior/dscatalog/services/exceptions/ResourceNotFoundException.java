package com.devsuperior.dscatalog.services.exceptions;

import java.io.Serializable;

// A camada de service possuí execeções próprias.
public class ResourceNotFoundException extends RuntimeException  {
    private static final long serialVersionUID = 1L;
    private String message;

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message) {super(message);};
}
