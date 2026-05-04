package cl.teatromoro.personal.exception;

public class IdDuplicadoException extends RuntimeException {
    
    /**
     * 
     * @param id
     */

    public IdDuplicadoException(String recurso, Long id) {
        super(recurso + " con ID " + id + " ya existe en el sistema.");
    }
}
