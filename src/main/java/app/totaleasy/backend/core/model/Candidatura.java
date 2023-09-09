package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Candidatura {

    private int numeroTSECandidato;

    private CargoEleicao cargoEleicao;

    private Partido partido;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.numeroTSECandidato,
            this.cargoEleicao.getCargo().getCodigoTSE(),
            this.cargoEleicao.getEleicao().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Candidatura candidatura = (Candidatura) object;

        return
            Objects.equals(this.numeroTSECandidato, candidatura.numeroTSECandidato) &&
            Objects.equals(this.cargoEleicao.getCargo().getCodigoTSE(), candidatura.cargoEleicao.getCargo().getCodigoTSE()) &&
            Objects.equals(this.cargoEleicao.getEleicao().getCodigoTSE(), candidatura.cargoEleicao.getEleicao().getCodigoTSE());
    }
}
