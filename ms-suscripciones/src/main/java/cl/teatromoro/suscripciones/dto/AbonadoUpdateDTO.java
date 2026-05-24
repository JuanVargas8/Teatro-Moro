package cl.teatromoro.suscripciones.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AbonadoUpdateDTO {

    @NotNull(message = "El plan es obligatorio")
    private Long planId;

    private LocalDate fechaFin;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}