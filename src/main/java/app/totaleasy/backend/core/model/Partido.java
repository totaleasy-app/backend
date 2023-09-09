package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Partido {

    private int numeroTSE;

    private String nome;

    private String sigla;

    public Partido(int numeroTSE) {
        this.numeroTSE = numeroTSE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Partido partido = (Partido) object;

        return Objects.equals(this.numeroTSE, partido.numeroTSE);
    }
}
