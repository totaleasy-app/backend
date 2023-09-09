package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class LocalVotacao {

    private int numeroTSE;

    private String nome;

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

        LocalVotacao localVotacao = (LocalVotacao) object;

        return
            Objects.equals(this.numeroTSE, localVotacao.numeroTSE) &&
            Objects.equals(this.zona.getNumeroTSE(), localVotacao.zona.getNumeroTSE()) &&
            Objects.equals(this.zona.getUF().getSigla(), localVotacao.zona.getUF().getSigla());
    }
}
