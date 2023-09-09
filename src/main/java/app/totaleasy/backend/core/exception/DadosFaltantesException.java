package app.totaleasy.backend.core.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class DadosFaltantesException extends RuntimeException {

    private final Map<String, String> dadosFaltantes;

    public DadosFaltantesException(String message) {
        super(message);
        this.dadosFaltantes = new LinkedHashMap<>();
    }

    public DadosFaltantesException(String message, Map<String, String> dadosFaltantes) {
        super(message);
        this.dadosFaltantes = dadosFaltantes;
    }
}
