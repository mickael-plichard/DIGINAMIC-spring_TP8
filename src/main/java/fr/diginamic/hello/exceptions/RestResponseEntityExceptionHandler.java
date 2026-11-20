package fr.diginamic.hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({ExceptionFonctionnelle.class})
    protected ResponseEntity<String> traiterErreurs(ExceptionFonctionnelle ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<String> traiterErreursValidation(MethodArgumentNotValidException ex) {
        StringBuilder erreurs = new StringBuilder();
        for (FieldError erreur : ex.getBindingResult().getFieldErrors()) {
            erreurs.append(erreur.getDefaultMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(erreurs.toString());
    }
}
