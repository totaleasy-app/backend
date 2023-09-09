package app.totaleasy.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Candidatura")
@Table(
    name = "candidatura",
    schema = "public",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_candidato", "id_cargo_eleicao"})}
)
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Candidatura implements Serializable {

    @Id
    @Column(name = "id_candidatura", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_candidato", nullable = false)
    private Candidato candidato;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_cargo_eleicao", nullable = false)
    private CargoEleicao cargoEleicao;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @OneToMany(mappedBy = "candidatura", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosCandidaturaBoletimUrna> apuracoesVotosBoletimUrna;

    public Candidatura() {
        this.apuracoesVotosBoletimUrna = new LinkedHashSet<>();
    }

    public Candidatura(Candidato candidato, CargoEleicao cargoEleicao, Partido partido) {
        this();
        this.candidato = candidato;
        this.cargoEleicao = cargoEleicao;
        this.partido = partido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.candidato.getCodigoTSE(),
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
            Objects.equals(this.candidato.getCodigoTSE(), candidatura.candidato.getCodigoTSE()) &&
            Objects.equals(this.cargoEleicao.getCargo().getCodigoTSE(), candidatura.cargoEleicao.getCargo().getCodigoTSE()) &&
            Objects.equals(this.cargoEleicao.getEleicao().getCodigoTSE(), candidatura.cargoEleicao.getEleicao().getCodigoTSE());
    }
}
