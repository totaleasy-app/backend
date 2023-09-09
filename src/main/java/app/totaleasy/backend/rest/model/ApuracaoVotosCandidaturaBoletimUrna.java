package app.totaleasy.backend.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

import java.util.Objects;

@Entity(name = "ApuracaoVotosCandidaturaBoletimUrna")
@Table(
    name = "apuracao_votos_candidatura_boletim_urna",
    schema = "public",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_candidatura", "id_boletim_urna"})}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class ApuracaoVotosCandidaturaBoletimUrna implements Serializable {

    @Id
    @Column(name = "id_apuracao_votos_candidatura_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_candidatura", nullable = false)
    private Candidatura candidatura;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_boletim_urna", nullable = false)
    private BoletimUrna boletimUrna;

    @Column(name = "total_votos_apurados", nullable = false)
    private Integer totalVotosApurados;

    public ApuracaoVotosCandidaturaBoletimUrna(Candidatura candidatura, BoletimUrna boletimUrna, Integer totalVotosApurados) {
        this.candidatura = candidatura;
        this.boletimUrna = boletimUrna;
        this.totalVotosApurados = totalVotosApurados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.candidatura.getCandidato().getCodigoTSE(),
            this.candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
            this.candidatura.getCargoEleicao().getEleicao().getCodigoTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosCandidaturaBoletimUrna apuracaoVotosCandidaturaBoletimUrna = (ApuracaoVotosCandidaturaBoletimUrna) object;

        return
            Objects.equals(this.candidatura.getCandidato().getCodigoTSE(), apuracaoVotosCandidaturaBoletimUrna.candidatura.getCandidato().getCodigoTSE()) &&
            Objects.equals(this.candidatura.getCargoEleicao().getCargo().getCodigoTSE(), apuracaoVotosCandidaturaBoletimUrna.candidatura.getCargoEleicao().getCargo().getCodigoTSE()) &&
            Objects.equals(this.candidatura.getCargoEleicao().getEleicao().getCodigoTSE(), apuracaoVotosCandidaturaBoletimUrna.candidatura.getCargoEleicao().getEleicao().getCodigoTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(), apuracaoVotosCandidaturaBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(), apuracaoVotosCandidaturaBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(), apuracaoVotosCandidaturaBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(), apuracaoVotosCandidaturaBoletimUrna.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE());
    }
}
