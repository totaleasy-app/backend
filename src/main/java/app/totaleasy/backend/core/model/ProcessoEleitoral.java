package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ProcessoEleitoral {

    private int codigoTSE;

    private String nome;

    private OrigemConfiguracaoProcessoEleitoral origemConfiguracao;

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ProcessoEleitoral processoEleitoral = (ProcessoEleitoral) object;

        return Objects.equals(this.codigoTSE, processoEleitoral.codigoTSE);
    }
}
