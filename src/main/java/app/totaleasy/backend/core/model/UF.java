package app.totaleasy.backend.core.model;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UF {

    AC (1, "Acre"),
    AL (2, "Alagoas"),
    AM (3, "Amazonas"),
    AP (4, "Amapá"),
    BA (5, "Bahia"),
    CE (6, "Ceará"),
    DF (7, "Distrito Federal"),
    ES (8, "Espírito Santo"),
    GO (9, "Goiás"),
    MA (10, "Maranhão"),
    MG (11, "Minas Gerais"),
    MS (12, "Mato Grosso do Sul"),
    MT (13, "Mato Grosso"),
    PA (14, "Pará"),
    PB (15, "Paraíba"),
    PE (16, "Pernambuco"),
    PI (17, "Piauí"),
    PR (18, "Paraná"),
    RJ (19, "Rio de Janeiro"),
    RN (20, "Rio Grande do Norte"),
    RO (21, "Rondônia"),
    RR (22, "Roraima"),
    RS (23, "Rio Grande do Sul"),
    SC (24, "Santa Cataria"),
    SE (25, "Sergipe"),
    SP (26, "São Paulo"),
    TO (27, "Tocantins"),
    ZZ (28, "Exterior");

    private final int codigo;

    private final String nome;

    public static Optional<UF> fromSigla(String sigla) {
        if (sigla == null) {
            throw new NullPointerException("A sigla da UF deve ser informada.");
        }

        for (UF value : values()) {
            if (value.name().equalsIgnoreCase(sigla)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public String getSigla() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
