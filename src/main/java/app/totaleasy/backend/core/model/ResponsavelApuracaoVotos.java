package app.totaleasy.backend.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ResponsavelApuracaoVotos {

    private int numeroJunta;

    private int numeroTurma;
}
