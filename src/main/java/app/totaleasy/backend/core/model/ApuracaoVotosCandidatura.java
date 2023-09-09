package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ApuracaoVotosCandidatura {

    private Candidatura candidatura;

    private int totalVotosApurados;

    public ApuracaoVotosCandidatura() {
        this.totalVotosApurados = 0;
    }

    public ApuracaoVotosCandidatura(Candidatura candidatura) {
        this();
        this.candidatura = candidatura;
    }

    public ApuracaoVotosCandidatura(Candidatura candidatura, int totalVotosApurados) {
        this(candidatura);
        this.totalVotosApurados = totalVotosApurados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.candidatura.getNumeroTSECandidato(),
            this.candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
            this.candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosCandidatura apuracaoVotosCandidatura = (ApuracaoVotosCandidatura) object;

        return
            Objects.equals(this.candidatura.getNumeroTSECandidato(), apuracaoVotosCandidatura.candidatura.getNumeroTSECandidato()) &&
            Objects.equals(this.candidatura.getCargoEleicao().getCargo().getCodigoTSE(), apuracaoVotosCandidatura.candidatura.getCargoEleicao().getCargo().getCodigoTSE()) &&
            Objects.equals(this.candidatura.getCargoEleicao().getEleicao().getCodigoTSE(), apuracaoVotosCandidatura.candidatura.getCargoEleicao().getEleicao().getCodigoTSE());
    }
}
