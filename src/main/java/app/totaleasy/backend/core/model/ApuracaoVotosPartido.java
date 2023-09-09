package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ApuracaoVotosPartido {

    private Partido partido;

    private CargoEleicao cargoEleicao;

    private int quantidadeVotosDeLegenda;

    private int totalVotosApurados;

    public ApuracaoVotosPartido() {
        this.quantidadeVotosDeLegenda = 0;
        this.totalVotosApurados = 0;
    }

    public ApuracaoVotosPartido(Partido partido, CargoEleicao cargoEleicao) {
        this();
        this.partido = partido;
        this.cargoEleicao = cargoEleicao;
    }

    public ApuracaoVotosPartido(Partido partido, CargoEleicao cargoEleicao, int quantidadeVotosDeLegenda) {
        this(partido, cargoEleicao);
        this.quantidadeVotosDeLegenda = quantidadeVotosDeLegenda;
    }

    public ApuracaoVotosPartido(Partido partido, CargoEleicao cargoEleicao, int quantidadeVotosDeLegenda, int totalVotosApurados) {
        this(partido, cargoEleicao, quantidadeVotosDeLegenda);
        this.totalVotosApurados = totalVotosApurados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.partido.getNumeroTSE(),
            this.cargoEleicao.getCargo().getCodigoTSE(),
            this.cargoEleicao.getEleicao().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosPartido apuracaoVotosPartido = (ApuracaoVotosPartido) object;

        return
            Objects.equals(this.partido.getNumeroTSE(), apuracaoVotosPartido.partido.getNumeroTSE()) &&
            Objects.equals(this.cargoEleicao.getCargo().getCodigoTSE(), apuracaoVotosPartido.cargoEleicao.getCargo().getCodigoTSE()) &&
            Objects.equals(this.cargoEleicao.getEleicao().getCodigoTSE(), apuracaoVotosPartido.cargoEleicao.getEleicao().getCodigoTSE());
    }
}
