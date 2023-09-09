package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class SecaoPleito {

    private Secao secao;

    private Pleito pleito;

    private int quantidadeEleitoresAptos;

    private Integer quantidadeEleitoresComparecentes;

    private Integer quantidadeEleitoresFaltosos;

    private Integer quantidadeEleitoresHabilitadosPorAnoNascimento;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.secao.getNumeroTSE(),
            this.secao.getZona().getNumeroTSE(),
            this.secao.getZona().getUF().getSigla(),
            this.pleito.getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        SecaoPleito secaoPleito = (SecaoPleito) object;

        return
            Objects.equals(this.secao.getNumeroTSE(), secaoPleito.secao.getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getNumeroTSE(), secaoPleito.secao.getZona().getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getUF().getSigla(), secaoPleito.secao.getZona().getUF().getSigla()) &&
            Objects.equals(this.pleito.getCodigoTSE(), secaoPleito.pleito.getCodigoTSE());
    }
}
