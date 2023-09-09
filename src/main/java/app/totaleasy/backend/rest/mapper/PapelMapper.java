package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.PapelRetrievalDTO;
import app.totaleasy.backend.rest.model.Papel;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PapelMapper {

    public PapelRetrievalDTO toPapelRetrievalDTO(Papel papel) {
        return new PapelRetrievalDTO(
            papel.getId(),
            papel.getNome(),
            papel.getDescricao()
        );
    }
}
