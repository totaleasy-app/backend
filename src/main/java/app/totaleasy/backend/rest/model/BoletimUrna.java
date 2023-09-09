package app.totaleasy.backend.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "BoletimUrna")
@Table(name = "boletim_urna", schema = "public")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class BoletimUrna implements Serializable {

    @Id
    @Column(name = "id_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_secao_pleito", unique = true, nullable = false)
    private SecaoPleito secaoPleito;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fase", nullable = false)
    private Fase fase;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_origem_boletim_urna", nullable = false)
    private OrigemBoletimUrna origem;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_urna_eletronica", nullable = false)
    private UrnaEletronica urnaEletronica;

    @Column(name = "assinatura", unique = true, nullable = false, length = 128, columnDefinition = "bpchar")
    private String assinatura;

    @Column(name = "qtde_total_qr_codes")
    private Integer quantidadeTotalQRCodes;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "horario_emissao")
    private LocalTime horarioEmissao;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<BoletimUrnaUsuario> usuarios;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<QRCodeBoletimUrna> qrCodes;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosCandidaturaBoletimUrna> apuracoesVotosCandidaturas;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosCargoBoletimUrna> apuracoesVotosCargos;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosPartidoBoletimUrna> apuracoesVotosPartidos;

    public BoletimUrna() {
        this.usuarios = new LinkedHashSet<>();
        this.qrCodes = new LinkedHashSet<>();
        this.apuracoesVotosCandidaturas = new LinkedHashSet<>();
        this.apuracoesVotosCargos = new LinkedHashSet<>();
        this.apuracoesVotosPartidos = new LinkedHashSet<>();
    }

    public BoletimUrna(SecaoPleito secaoPleito, Fase fase, OrigemBoletimUrna origem, UrnaEletronica urnaEletronica, String assinatura, Integer quantidadeTotalQRCodes, LocalDate dataEmissao, LocalTime horarioEmissao) {
        this();
        this.secaoPleito = secaoPleito;
        this.fase = fase;
        this.origem = origem;
        this.urnaEletronica = urnaEletronica;
        this.assinatura = assinatura;
        this.quantidadeTotalQRCodes = quantidadeTotalQRCodes;
        this.dataEmissao = dataEmissao;
        this.horarioEmissao = horarioEmissao;
    }

    public Set<QRCodeBoletimUrna> getQRCodes() {
        return this.qrCodes;
    }

    public void setQRCodes(Set<QRCodeBoletimUrna> qrCodes) {
        this.qrCodes = qrCodes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.secaoPleito.getSecao().getNumeroTSE(),
            this.secaoPleito.getSecao().getZona().getNumeroTSE(),
            this.secaoPleito.getSecao().getZona().getUF().getSigla(),
            this.secaoPleito.getPleito().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        BoletimUrna boletimUrna = (BoletimUrna) object;

        return
            Objects.equals(this.secaoPleito.getSecao().getNumeroTSE(), boletimUrna.secaoPleito.getSecao().getNumeroTSE()) &&
            Objects.equals(this.secaoPleito.getSecao().getZona().getNumeroTSE(), boletimUrna.secaoPleito.getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.secaoPleito.getSecao().getZona().getUF().getSigla(), boletimUrna.secaoPleito.getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.secaoPleito.getPleito().getCodigoTSE(), boletimUrna.secaoPleito.getPleito().getCodigoTSE());
    }
}
