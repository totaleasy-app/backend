package app.totaleasy.backend.rest.exception;

public class TokenExpiradoException extends RuntimeException {

    public TokenExpiradoException(String message) {
        super(message);
    }
}
