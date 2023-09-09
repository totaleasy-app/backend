package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.OcupacaoRetrievalDTO;
import app.totaleasy.backend.rest.model.Ocupacao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OcupacaoMapper {

    public OcupacaoRetrievalDTO toOcupacaoRetrievalDTO(Ocupacao ocupacao) {
        return new OcupacaoRetrievalDTO(
            ocupacao.getId(),
            ocupacao.getCodigoTSE(),
            ocupacao.getNome()
        );
    }
}
