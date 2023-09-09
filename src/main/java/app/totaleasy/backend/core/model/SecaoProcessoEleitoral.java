package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class SecaoProcessoEleitoral {

    private Secao secao;

    private ProcessoEleitoral processoEleitoral;

    private LocalVotacao localVotacao;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.secao.getNumeroTSE(),
            this.secao.getZona().getNumeroTSE(),
            this.secao.getZona().getUF().getSigla(),
            this.processoEleitoral.getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        SecaoProcessoEleitoral secaoProcessoEleitoral = (SecaoProcessoEleitoral) object;

        return
            Objects.equals(this.secao.getNumeroTSE(), secaoProcessoEleitoral.secao.getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getNumeroTSE(), secaoProcessoEleitoral.secao.getZona().getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getUF().getSigla(), secaoProcessoEleitoral.secao.getZona().getUF().getSigla()) &&
            Objects.equals(this.processoEleitoral.getCodigoTSE(), secaoProcessoEleitoral.processoEleitoral.getCodigoTSE());
    }
}
