package com.devsuperior.dscatalog.resources.exceptions;


import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// Vai capturar as exceções que acontecem na camada de controladores
@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // pega o tipo de exceção é referido para classe.
    public ResponseEntity<StandardError> resourceNotEntity(ResourceNotFoundException exception, HttpServletRequest request){
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(badRequest.value()); // Pega o tipo enumerado e converte para inteiro.
        error.setError("Ressource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(badRequest).body(error);
    }
}
