package app.totaleasy.backend.core.model.complemento;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Candidato {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private String codigoTSE;

    private String nome;

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Candidato candidato = (Candidato) object;

        return Objects.equals(this.codigoTSE, candidato.codigoTSE);
    }
}
