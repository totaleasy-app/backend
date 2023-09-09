package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class EmissaoBoletimUrna {

    private LocalDate data;

    private LocalTime horario;
}
