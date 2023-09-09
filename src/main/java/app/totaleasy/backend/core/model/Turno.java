package app.totaleasy.backend.core.model;

import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Turno {

    PRIMEIRO (1, "1º turno"),
    SEGUNDO  (2, "2º turno");

    private final int numero;

    private final String nome;

    public static Optional<Turno> fromNumero(int numero) {
        for (Turno value : values()) {
            if (value.numero == numero) {
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
