package cl.teatromoro.promociones.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private LocalDateTime timestamp;
    private int status;          // 404, 500, etc
    private String error;        // NOT_FOUND, BAD_REQUEST

    private String message;      // mensaje principal
    private List<String> errors; // lista de errores (validaciones)

    private String path;         // endpoint
}
