package app.totaleasy.backend.core.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Zona {

    private Integer numeroTSE;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private UF uf;

    public UF getUF() {
        return this.uf;
    }

    public void setUF(UF uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroTSE, this.uf.getSigla());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        Zona zona = (Zona) object;

        return
            Objects.equals(this.numeroTSE, zona.numeroTSE) &&
            Objects.equals(this.uf.getSigla(), zona.uf.getSigla());
    }
}
