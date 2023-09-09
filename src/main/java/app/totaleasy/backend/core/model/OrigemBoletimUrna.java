package app.totaleasy.backend.core.model;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrigemBoletimUrna {

    SOFTWARE_DE_VOTACAO  ("Software de Votação", "Vota"),
    RECUPERADOR_DE_DADOS ("Recuperador de Dados", "RED"),
    SISTEMA_DE_APURACAO  ("Sistema de Apuração", "SA");

    private final String nome;

    private final String nomeAbreviado;

    public static Optional<OrigemBoletimUrna> fromNomeAbreviado(String nomeAbreviado) {
        if (nomeAbreviado == null) {
            throw new NullPointerException("A origem do boletim de urna deve ser informada.");
        }

        for (OrigemBoletimUrna value : values()) {
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
