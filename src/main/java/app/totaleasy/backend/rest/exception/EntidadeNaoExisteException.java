package app.totaleasy.backend.rest.exception;

public class EntidadeNaoExisteException extends RuntimeException {

    public EntidadeNaoExisteException(String message) {
        super(message);
    }
}
