package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class TipoCargo {

    private int codigoTSE;

    private String nome;

    public TipoCargo(int codigoTSE) {
        this.codigoTSE = codigoTSE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        TipoCargo tipoCargo = (TipoCargo) object;

        return Objects.equals(this.codigoTSE, tipoCargo.codigoTSE);
    }
}
