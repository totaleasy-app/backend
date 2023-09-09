package app.totaleasy.backend.rest.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import java.util.Objects;

@Entity(name = "AgregacaoSecao")
@Table(
    name = "agregacao_secao",
    schema = "public",
    uniqueConstraints = {@UniqueConstraint(columnNames = {
        "id_secao_principal", "id_secao_agregada", "id_processo_eleitoral"
    })}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class AgregacaoSecao implements Serializable {

    @Id
    @Column(name = "id_agregacao_secao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_secao_principal", nullable = false)
    private Secao secaoPrincipal;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_secao_agregada", nullable = false)
    private Secao secaoAgregada;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_processo_eleitoral", nullable = false)
    private ProcessoEleitoral processoEleitoral;

    public AgregacaoSecao(Secao secaoPrincipal, Secao secaoAgregada, ProcessoEleitoral processoEleitoral) {
        this.secaoPrincipal = secaoPrincipal;
        this.secaoAgregada = secaoAgregada;
        this.processoEleitoral = processoEleitoral;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.secaoPrincipal.getNumeroTSE(),
            this.secaoPrincipal.getZona().getNumeroTSE(),
            this.secaoPrincipal.getZona().getUF().getSigla(),
            this.secaoAgregada.getNumeroTSE(),
            this.secaoAgregada.getZona().getNumeroTSE(),
            this.secaoAgregada.getZona().getUF().getSigla(),
            this.processoEleitoral.getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        AgregacaoSecao agregacaoSecao = (AgregacaoSecao) object;

        return Objects.equals(this.hashCode(), agregacaoSecao.hashCode());
    }
}
