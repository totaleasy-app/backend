package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.PartidoRetrievalDTO;
import app.totaleasy.backend.rest.model.Partido;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidoMapper {

    public PartidoRetrievalDTO toPartidoRetrievalDTO(Partido partido) {
        return new PartidoRetrievalDTO(
            partido.getId(),
            partido.getNumeroTSE(),
            partido.getNome(),
            partido.getSigla()
        );
    }
}
