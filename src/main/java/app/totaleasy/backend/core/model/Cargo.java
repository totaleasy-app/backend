package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Cargo {

    private int codigoTSE;

    private String nome;

    private String nomeAbreviado;

    private TipoCargo tipo;

    private String versaoPacoteDados;

    public Cargo (int codigoTSE) {
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

        Cargo cargo = (Cargo) object;

        return Objects.equals(this.codigoTSE, cargo.codigoTSE);
    }
}
