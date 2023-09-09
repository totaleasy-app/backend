package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class CargoEleicao {

    private Cargo cargo;

    private Eleicao eleicao;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.cargo.getCodigoTSE(),
            this.eleicao.getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        CargoEleicao cargoEleicao = (CargoEleicao) object;

        return
            Objects.equals(this.cargo.getCodigoTSE(), cargoEleicao.cargo.getCodigoTSE()) &&
            Objects.equals(this.eleicao.getCodigoTSE(), cargoEleicao.eleicao.getCodigoTSE());
    }
}
