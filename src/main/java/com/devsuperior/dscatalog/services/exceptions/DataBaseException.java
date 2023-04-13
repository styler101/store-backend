package com.devsuperior.dscatalog.services.exceptions;

import java.io.Serializable;

public class DataBaseException extends RuntimeException {
    private String message;

    public DataBaseException(){}

    public DataBaseException(String message){
        super(message);
    }
}
