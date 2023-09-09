package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.CorRacaRetrievalDTO;
import app.totaleasy.backend.rest.model.CorRaca;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorRacaMapper {

    public CorRacaRetrievalDTO toCorRacaRetrievalDTO(CorRaca corRaca) {
        return new CorRacaRetrievalDTO(
            corRaca.getId(),
            corRaca.getCodigoTSE(),
            corRaca.getNome()
        );
    }
}
