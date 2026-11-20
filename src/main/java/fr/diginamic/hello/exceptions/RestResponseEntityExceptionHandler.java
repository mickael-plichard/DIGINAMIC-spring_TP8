package fr.diginamic.hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({ExceptionFonctionnelle.class})
    protected ResponseEntity<String> traiterErreurs(ExceptionFonctionnelle ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
