package app.totaleasy.backend.core.model.complemento;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Pleito {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private int codigoTSE;

    private String nome;

    private String data;

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Pleito pleito = (Pleito) object;

        return Objects.equals(this.codigoTSE, pleito.codigoTSE);
    }
}
