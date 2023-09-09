package app.totaleasy.backend.core.model.complemento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class CandidaturaPartido {

    private Partido partido;

    private List<Candidatura> candidaturas;

    public Map<CandidatoId, Candidato> getCandidatosTitulares() {
        Map<CandidatoId, Candidato> candidatosTitulares = new HashMap<>();

        for (Candidatura candidatura : this.candidaturas) {
            candidatosTitulares.put(
                new CandidatoId(candidatura.getNumeroTSECandidato(), 0),
                candidatura.getTitular()
            );
        }

        return candidatosTitulares;
    }
}
