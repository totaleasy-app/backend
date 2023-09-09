package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.RegiaoRetrievalDTO;
import app.totaleasy.backend.rest.model.Regiao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegiaoMapper {

    public RegiaoRetrievalDTO toRegiaoRetrievalDTO(Regiao regiao) {
        return new RegiaoRetrievalDTO(
            regiao.getId(),
            regiao.getCodigoIBGE(),
            regiao.getNome(),
            regiao.getSigla()
        );
    }
}
