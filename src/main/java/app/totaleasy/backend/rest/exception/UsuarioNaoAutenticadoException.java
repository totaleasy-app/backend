package app.totaleasy.backend.rest.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {

    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }
}
