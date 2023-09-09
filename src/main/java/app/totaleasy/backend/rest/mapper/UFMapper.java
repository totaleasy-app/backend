package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.UFRetrievalDTO;
import app.totaleasy.backend.rest.model.UF;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UFMapper {

    private final RegiaoMapper regiaoMapper;

    public UFRetrievalDTO toUFRetrievalDTO(UF uf) {
        return new UFRetrievalDTO(
            uf.getId(),
            uf.getCodigoIBGE(),
            uf.getNome(),
            uf.getSigla(),
            this.regiaoMapper.toRegiaoRetrievalDTO(uf.getRegiao())
        );
    }
}
