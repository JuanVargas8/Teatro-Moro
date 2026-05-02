package cl.teatromoro.catalogo.exception;

public class RecursoDuplicadoException extends RuntimeException {
    
    public RecursoDuplicadoException(String message) {
        super(message);
    }

    public RecursoDuplicadoException(String recurso, String campo, String valor) {
        super(recurso + " ya existe con " + campo + ": " + valor);
    }

}
