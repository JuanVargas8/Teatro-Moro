package cl.teatromoro.usuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarValidaciones(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarRuntime(RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarConstraintViolation(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();
    }


}