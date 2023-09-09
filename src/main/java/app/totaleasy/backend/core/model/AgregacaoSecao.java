package app.totaleasy.backend.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class AgregacaoSecao {

    private Secao secaoPrincipal;

    private Secao secaoAgregada;

    private ProcessoEleitoral processoEleitoral;

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

        return
            Objects.equals(this.secaoPrincipal.getNumeroTSE(), agregacaoSecao.secaoPrincipal.getNumeroTSE()) &&
            Objects.equals(this.secaoPrincipal.getZona().getNumeroTSE(), agregacaoSecao.secaoPrincipal.getZona().getNumeroTSE()) &&
            Objects.equals(this.secaoPrincipal.getZona().getUF().getSigla(), agregacaoSecao.secaoPrincipal.getZona().getUF().getSigla()) &&
            Objects.equals(this.secaoAgregada.getNumeroTSE(), agregacaoSecao.secaoAgregada.getNumeroTSE()) &&
            Objects.equals(this.secaoAgregada.getZona().getNumeroTSE(), agregacaoSecao.secaoAgregada.getZona().getNumeroTSE()) &&
            Objects.equals(this.secaoAgregada.getZona().getUF().getSigla(), agregacaoSecao.secaoAgregada.getZona().getUF().getSigla()) &&
            Objects.equals(this.processoEleitoral.getCodigoTSE(), agregacaoSecao.processoEleitoral.getCodigoTSE());
    }
}
