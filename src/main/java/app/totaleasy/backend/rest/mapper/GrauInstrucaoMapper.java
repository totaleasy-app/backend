package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.GrauInstrucaoRetrievalDTO;
import app.totaleasy.backend.rest.model.GrauInstrucao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrauInstrucaoMapper {

    public GrauInstrucaoRetrievalDTO toGrauInstrucaoRetrievalDTO(GrauInstrucao grauInstrucao) {
        return new GrauInstrucaoRetrievalDTO(
            grauInstrucao.getId(),
            grauInstrucao.getCodigoTSE(),
            grauInstrucao.getNome()
        );
    }
}
