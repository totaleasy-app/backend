package app.totaleasy.backend.core.model.complemento;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Municipio {

    @SerializedName(value = "numero", alternate = {"codigoTSE"})
    private int codigoTSE;

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

        Municipio municipio = (Municipio) object;

        return Objects.equals(this.codigoTSE, municipio.codigoTSE);
    }
}
