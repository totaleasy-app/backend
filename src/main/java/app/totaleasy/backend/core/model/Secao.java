package app.totaleasy.backend.core.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Secao {

    private int numeroTSE;

    private Zona zona;

    private Municipio municipio;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.numeroTSE,
            this.zona.getNumeroTSE(),
            this.zona.getUF().getSigla()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Secao secao = (Secao) object;

        return
            Objects.equals(this.numeroTSE, secao.numeroTSE) &&
            Objects.equals(this.zona.getNumeroTSE(), secao.zona.getNumeroTSE()) &&
            Objects.equals(this.zona.getUF().getSigla(), secao.zona.getUF().getSigla());
    }
}
