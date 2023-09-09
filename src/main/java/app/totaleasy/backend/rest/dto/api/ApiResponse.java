package app.totaleasy.backend.rest.dto.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"status", "data", "error", "timestamp"})
public class ApiResponse {

    private final HttpStatus status;

    private Object data;

    private Object error;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    public ApiResponse(HttpStatus status, Object object) {
        if (status.isError()) {
            this.error = object;
        } else {
            this.data = object;
        }

        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
