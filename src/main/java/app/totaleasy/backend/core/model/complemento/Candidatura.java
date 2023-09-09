package app.totaleasy.backend.core.model.complemento;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Candidatura {

    @SerializedName(value = "numero", alternate = {"numeroTSECandidato"})
    private int numeroTSECandidato;

    private Candidato titular;

    private List<Candidato> suplentes;
}
