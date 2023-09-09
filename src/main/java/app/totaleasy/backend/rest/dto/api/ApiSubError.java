package app.totaleasy.backend.rest.dto.api;

import lombok.Getter;

@Getter
public abstract class ApiSubError {

    protected final String message;

    protected ApiSubError(String message) {
        this.message = message;
    }
}
