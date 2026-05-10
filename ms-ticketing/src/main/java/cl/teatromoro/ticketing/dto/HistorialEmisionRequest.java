package cl.teatromoro.ticketing.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HistorialEmisionRequest {

    private LocalDate fechaEmision;
    private String canalVenta;
    private Long idTicket;

}
