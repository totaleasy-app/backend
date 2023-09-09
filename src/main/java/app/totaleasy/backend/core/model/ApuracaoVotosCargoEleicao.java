package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ApuracaoVotosCargoEleicao {

    private CargoEleicao cargoEleicao;

    private int quantidadeEleitoresAptos;

    private int quantidadeComparecimentoCargoSemCandidatos;

    private int quantidadeVotosNominais;

    private int quantidadeVotosDeLegenda;

    private int quantidadeVotosEmBranco;

    private int quantidadeVotosNulos;

    private int totalVotosApurados;

    public ApuracaoVotosCargoEleicao() {
        this.quantidadeEleitoresAptos = 0;
        this.quantidadeVotosNominais = 0;
        this.quantidadeVotosEmBranco = 0;
        this.quantidadeVotosNulos = 0;
        this.totalVotosApurados = 0;
    }

    public ApuracaoVotosCargoEleicao(CargoEleicao cargoEleicao) {
        this();
        this.cargoEleicao = cargoEleicao;
    }

    public ApuracaoVotosCargoEleicao(CargoEleicao cargoEleicao, int quantidadeEleitoresAptos) {
        this(cargoEleicao);
        this.quantidadeEleitoresAptos = quantidadeEleitoresAptos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.cargoEleicao.getCargo().getCodigoTSE(),
            this.cargoEleicao.getEleicao().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosCargoEleicao apuracaoVotosCargoEleicao = (ApuracaoVotosCargoEleicao) object;

        return
            Objects.equals(this.cargoEleicao.getCargo().hashCode(), apuracaoVotosCargoEleicao.cargoEleicao.getCargo().hashCode()) &&
            Objects.equals(this.cargoEleicao.getEleicao().hashCode(), apuracaoVotosCargoEleicao.cargoEleicao.getEleicao().hashCode());
    }
}
