package app.totaleasy.backend.rest.controller;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import app.totaleasy.backend.rest.dto.api.ApiError;
import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.exception.EntidadeJaExisteException;
import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        @NotNull MissingServletRequestParameterException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.BAD_REQUEST,
                new ApiError(
                    HttpStatus.BAD_REQUEST,
                    String.format("O parâmetro '%s' está faltando", ex.getParameterName()),
                    ex
                )
            )
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Erro de validação");

        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());

        return this.buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, apiError));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Erro de validação");

        apiError.addValidationErrors(ex.getConstraintViolations());

        return this.buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, apiError));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        @NotNull HttpMessageNotReadableException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.BAD_REQUEST,
                new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Requisição JSON malformada",
                    ex
                )
            )
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        @NotNull HttpMessageNotWritableException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao escrever saída JSON",
                    ex
                )
            )
        );
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        @NotNull NoHandlerFoundException ex,
        @NotNull HttpHeaders headers,
        @NotNull HttpStatusCode status,
        @NotNull WebRequest request
    ) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.BAD_REQUEST,
                new ApiError(
                    HttpStatus.BAD_REQUEST,
                    String.format(
                        "Não foi encontrado o método '%s' para a URL '%s'",
                        ex.getHttpMethod(),
                        ex.getRequestURL()
                    ),
                    ex.getMessage()
                )
            )
        );
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
        DataIntegrityViolationException ex,
        WebRequest request
    ) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return this.buildResponseEntity(
                new ApiResponse(
                    HttpStatus.CONFLICT,
                    new ApiError(
                        HttpStatus.CONFLICT,
                        "Erro de banco de dados",
                        ex.getCause()
                    )
                )
            );
        }

        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex)
            )
        );
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex,
        WebRequest request
    ) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.BAD_REQUEST,
                new ApiError(
                    HttpStatus.BAD_REQUEST,
                    String.format(
                        "Não foi possível converter o parâmetro '%s', de valor '%s', para o tipo '%s'",
                        ex.getName(),
                        ex.getValue(),
                        Objects.requireNonNull(ex.getRequiredType()).getSimpleName()
                    ),
                    ex.getMessage()
                )
            )
        );
    }

    @ExceptionHandler(value = EntidadeJaExisteException.class)
    public ResponseEntity<Object> handleEntidadeJaExisteException(EntidadeJaExisteException ex) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.CONFLICT,
                new ApiError(HttpStatus.CONFLICT, ex.getMessage())
            )
        );
    }

    @ExceptionHandler(value = EntidadeNaoExisteException.class)
    public ResponseEntity<Object> handleEntidadeNaoExisteException(EntidadeNaoExisteException ex) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.NOT_FOUND,
                new ApiError(HttpStatus.NOT_FOUND, ex.getMessage())
            )
        );
    }

    @ExceptionHandler(value = ValorInvalidoException.class)
    public ResponseEntity<Object> handleValorInvalidoException(ValorInvalidoException ex) {
        return this.buildResponseEntity(
            new ApiResponse(
                HttpStatus.BAD_REQUEST,
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage())
            )
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
