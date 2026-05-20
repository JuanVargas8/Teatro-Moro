package cl.teatromoro.suscripciones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarRuntime(RuntimeException ex) {
        return Map.of("mensaje", ex.getMessage());
    }
}