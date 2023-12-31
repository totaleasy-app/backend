package app.totaleasy.backend.core.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Objects;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class UrnaEletronica {

    private int numeroSerie;

    private String codigoIdentificacaoCarga;

    private String versaoSoftware;

    private LocalDate dataAbertura;

    private LocalTime horarioAbertura;

    private LocalDate dataFechamento;

    private LocalTime horarioFechamento;

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroSerie);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        UrnaEletronica urnaEletronica = (UrnaEletronica) object;

        return Objects.equals(this.numeroSerie, urnaEletronica.numeroSerie);
    }
}
