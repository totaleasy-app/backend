package app.totaleasy.backend.core.model;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrigemConfiguracaoProcessoEleitoral {

    ELEICAO_LEGAL_OFICIAL ("Eleição legal oficial", "LEG"),
    ELEICAO_COMUNITARIA   ("Eleicao comunitária", "COM");

    private final String nome;

    private final String nomeAbreviado;

    public static Optional<OrigemConfiguracaoProcessoEleitoral> fromNomeAbreviado(String nomeAbreviado) {
        if (nomeAbreviado == null) {
            throw new NullPointerException("A origem da configuração do processo eleitoral deve ser informada.");
        }

        for (OrigemConfiguracaoProcessoEleitoral value : values()) {
            if (value.nomeAbreviado.equalsIgnoreCase(nomeAbreviado)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public String toStringURLVerificacaoAssinatura() {
        if (this == OrigemConfiguracaoProcessoEleitoral.ELEICAO_LEGAL_OFICIAL) {
            return "LEGAL";
        }

        return "COMUNITARIA";
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
