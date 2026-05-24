package cl.teatromoro.suscripciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public class PlanDTO {

    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    @NotNull(message = "Precio obligatorio")
    @PositiveOrZero(message = "Precio debe ser mayor o igual a 0")
    private Double precio;

    @NotBlank(message = "Beneficios obligatorios")
    private String beneficios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}