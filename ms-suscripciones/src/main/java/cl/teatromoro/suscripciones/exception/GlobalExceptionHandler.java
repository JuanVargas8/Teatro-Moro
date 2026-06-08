package cl.teatromoro.suscripciones.exception;

import cl.teatromoro.suscripciones.dto.ErrorResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> manejarNotFound(
            ResourceNotFoundException ex) {

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRuntime(
            RuntimeException ex) {

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value()
                );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        FieldError fieldError =
                ex.getBindingResult().getFieldError();

        String mensaje =
                (fieldError != null)
                        ? fieldError.getDefaultMessage()
                        : "Error de validación";

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        mensaje,
                        HttpStatus.BAD_REQUEST.value()
                );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}