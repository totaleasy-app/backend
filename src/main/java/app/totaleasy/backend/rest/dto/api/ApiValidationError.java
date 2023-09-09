package app.totaleasy.backend.rest.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiValidationError extends ApiSubError {

    private String object;

    private String field;

    private Object rejectedValue;

    public ApiValidationError(String object, String message) {
        super(message);

        this.object = object;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this(object, message);

        this.field = field;
        this.rejectedValue = rejectedValue;
    }
}
