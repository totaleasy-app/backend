package app.totaleasy.backend.rest.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.Objects;

@Entity(name = "BoletimUrnaUsuario")
@Table(
    name = "boletim_urna_usuario",
    schema = "public",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_boletim_urna", "id_usuario"})}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class BoletimUrnaUsuario implements Serializable {

    @Id
    @Column(name = "id_boletim_urna_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_boletim_urna", nullable = false)
    private BoletimUrna boletimUrna;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "coletado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime coletadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    public BoletimUrnaUsuario(BoletimUrna boletimUrna, Usuario usuario) {
        this.boletimUrna = boletimUrna;
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(),
            this.usuario.getUsername()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        BoletimUrnaUsuario boletimUrnaUsuario = (BoletimUrnaUsuario) object;

        return
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(), boletimUrnaUsuario.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(), boletimUrnaUsuario.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(), boletimUrnaUsuario.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(), boletimUrnaUsuario.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE()) &&
            Objects.equals(this.usuario.getUsername(), boletimUrnaUsuario.usuario.getUsername());
    }
}
