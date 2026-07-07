package cl.teatromoro.suscripciones.dto;

import cl.teatromoro.suscripciones.model.Plan;
import org.springframework.hateoas.RepresentationModel;

public class PlanResponseDTO extends RepresentationModel<PlanResponseDTO> {

    private Long id;
    private String nombre;
    private Double precio;
    private String beneficios;

    public PlanResponseDTO(
            Long id,
            String nombre,
            Double precio,
            String beneficios) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.beneficios = beneficios;
    }

    public PlanResponseDTO(Plan plan) {

        this.id = plan.getId();
        this.nombre = plan.getNombre();
        this.precio = plan.getPrecio();
        this.beneficios = plan.getBeneficios();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getBeneficios() {
        return beneficios;
    }
}