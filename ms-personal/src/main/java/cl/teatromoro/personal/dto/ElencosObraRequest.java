package cl.teatromoro.personal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElencosObraRequest {
    private Long idObra;
    private Long idPersonal;
    private String rolEnObra;
}
