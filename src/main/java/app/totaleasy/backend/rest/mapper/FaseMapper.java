package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.FaseRetrievalDTO;
import app.totaleasy.backend.rest.model.Fase;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FaseMapper {

    public FaseRetrievalDTO toFaseRetrievalDTO(Fase fase) {
        return new FaseRetrievalDTO(
            fase.getId(),
            fase.getCodigoTSE(),
            fase.getNome()
        );
    }
}
