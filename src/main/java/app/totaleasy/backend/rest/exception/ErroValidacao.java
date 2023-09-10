package app.totaleasy.backend.rest.exception;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.ConstraintViolation;

import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"field", "message"})
public class ErroValidacao extends Erro {

    private final String field;

    public ErroValidacao(String field, String message) {
        super(message);
        this.field = field;
    }

    public ErroValidacao(ConstraintViolation<?> constraintViolation) {
        this(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }

    public ErroValidacao(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
