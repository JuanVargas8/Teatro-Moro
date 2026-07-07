package cl.teatromoro.common.exception;

public class DuplicateResourceException extends RuntimeException {

    /**
     * Constructor genérico para registros duplicados.
     *
     * @param entity Nombre de la entidad (ej: "Usuario", "Plan").
     * @param field Campo duplicado (ej: "Email", "Nombre").
     * @param value Valor que genera el conflicto.
     * @param description Descripción adicional.
     */
    public DuplicateResourceException(
            String entity,
            String field,
            Object value,
            String description) {

        super(String.format(
                "%s con %s igual a '%s' ya existe en el sistema, descrito por '%s'.",
                entity,
                field,
                value != null ? value.toString() : "N/A",   
                description
        ));
    }

    /**
     * Constructor con mensaje personalizado.
     */
    public DuplicateResourceException(String message) {
        super(message);
    }
}