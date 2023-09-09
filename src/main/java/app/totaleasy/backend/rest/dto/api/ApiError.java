package app.totaleasy.backend.rest.dto.api;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.internal.engine.path.PathImpl;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.ConstraintViolation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder(value = {"status", "message", "debugMessage", "subErrors", "timestamp"})
public class ApiError {

    private final HttpStatus status;

    private String message;

    private String debugMessage;

    private List<ApiSubError> subErrors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    public ApiError(HttpStatus status) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, String debugMessage) {
        this(status);
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ApiError(HttpStatus status, Throwable throwable) {
        this(status, "Erro inesperado");
        this.debugMessage = throwable.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable throwable) {
        this(status, throwable);
        this.message = message;
    }

    private void addSubError(ApiSubError subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }

        this.subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        this.addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        this.addSubError(new ApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
            fieldError.getObjectName(),
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage()
        );
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> objectErrors) {
        objectErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ConstraintViolation<?> constraintViolation) {
        this.addValidationError(
            constraintViolation.getRootBeanClass().getSimpleName(),
            ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
            constraintViolation.getInvalidValue(),
            constraintViolation.getMessage()
        );
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}
