package cl.teatromoro.informes.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SnapshotVentasRequest {
    private LocalDate fecha;
    private Double totalRecaudado;
    private Integer totalTickets;
}
