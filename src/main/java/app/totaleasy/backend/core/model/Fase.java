package app.totaleasy.backend.core.model;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Fase {

    SIMULADO    (1, "Simulado", "S"),
    OFICIAL     (2, "Oficial", "O"),
    TREINAMENTO (3, "Treinamento", "T");

    private final int codigoTSE;

    private final String nome;

    private final String nomeAbreviado;

    public static Optional<Fase> fromNomeAbreviado(String nomeAbreviado) {
        if (nomeAbreviado == null) {
            throw new NullPointerException("A fase dos dados do boletim de urna deve ser informada.");
        }

        for (Fase value : values()) {
            if (value.nomeAbreviado.equalsIgnoreCase(nomeAbreviado)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
