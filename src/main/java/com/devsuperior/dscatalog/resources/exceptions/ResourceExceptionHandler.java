package com.devsuperior.dscatalog.resources.exceptions;


import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

// Vai capturar as exceções que acontecem na camada de controladores
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // pega o tipo de exceção é referido para classe.
    public ResponseEntity<StandardError> resourceNotEntity(ResourceNotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(notFound.value()); // Pega o tipo enumerado e converte para inteiro.
        error.setError("Ressource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(notFound).body(error);
    }

    @ExceptionHandler(DataBaseException.class) // pega o tipo de exceção é referido para classe.
    public ResponseEntity<StandardError> resourceNotEntity(@org.jetbrains.annotations.NotNull DataBaseException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(badRequest.value()); // Pega o tipo enumerado e converte para inteiro.
        error.setError("Ressource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(badRequest).body(error);
    }

}
