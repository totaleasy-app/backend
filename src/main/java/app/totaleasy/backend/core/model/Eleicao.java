package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Eleicao {

    private int codigoTSE;

    private Pleito pleito;

    private String nome;

    private int ano;

    private int quantidadeVotosCargosMajoritarios;

    private int quantidadeVotosCargosProporcionais;

    public Eleicao(int codigoTSE, Pleito pleito, int ano) {
        this.codigoTSE = codigoTSE;
        this.pleito = pleito;
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Eleicao eleicao = (Eleicao) object;

        return Objects.equals(this.codigoTSE, eleicao.codigoTSE);
    }
}
