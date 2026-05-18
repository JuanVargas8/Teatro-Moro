package cl.teatromoro.suscripciones.dto;

import jakarta.validation.constraints.NotNull;

public class AbonadoDTO {

    @NotNull(message = "usuarioId obligatorio")
    private Long usuarioId;

    @NotNull(message = "planId obligatorio")
    private Long planId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}