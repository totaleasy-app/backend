package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.EstadoCivilRetrievalDTO;
import app.totaleasy.backend.rest.model.EstadoCivil;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstadoCivilMapper {

    public EstadoCivilRetrievalDTO toEstadoCivilRetrievalDTO(EstadoCivil estadoCivil) {
        return new EstadoCivilRetrievalDTO(
            estadoCivil.getId(),
            estadoCivil.getCodigoTSE(),
            estadoCivil.getNome()
        );
    }
}
