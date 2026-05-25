package cl.teatromoro.suscripciones.dto;

import java.time.LocalDate;

public class AbonadoResponseDTO {

    private Long id;
    private Long usuarioId;
    private PlanResponseDTO plan;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public AbonadoResponseDTO(
            Long id,
            Long usuarioId,
            PlanResponseDTO plan,
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.plan = plan;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public PlanResponseDTO getPlan() {
        return plan;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}