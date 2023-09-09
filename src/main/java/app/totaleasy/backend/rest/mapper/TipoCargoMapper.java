package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.TipoCargoRetrievalDTO;
import app.totaleasy.backend.rest.model.TipoCargo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TipoCargoMapper {

    public TipoCargoRetrievalDTO toTipoCargoRetrievalDTO(TipoCargo tipoCargo) {
        return new TipoCargoRetrievalDTO(
            tipoCargo.getId(),
            tipoCargo.getCodigoTSE(),
            tipoCargo.getNome()
        );
    }
}
