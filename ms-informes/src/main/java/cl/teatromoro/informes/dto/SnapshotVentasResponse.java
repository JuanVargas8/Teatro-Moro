package cl.teatromoro.informes.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SnapshotVentasResponse {
    private Long id;
    private LocalDate fecha;
    private Double totalRecaudado;
    private Integer totalTickets;
}
