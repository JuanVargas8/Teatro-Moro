package cl.teatromoro.ticketing.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class HistorialEmisionResponse {  

    private Long id;
    private LocalDate fechaEmision;
    private String canalVenta;
    
}
