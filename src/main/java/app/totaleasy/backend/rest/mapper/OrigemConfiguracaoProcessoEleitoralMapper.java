package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.OrigemConfiguracaoProcessoEleitoralRetrievalDTO;
import app.totaleasy.backend.rest.model.OrigemConfiguracaoProcessoEleitoral;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrigemConfiguracaoProcessoEleitoralMapper {

    public OrigemConfiguracaoProcessoEleitoralRetrievalDTO toOrigemConfiguracaoProcessoEleitoralRetrievalDTO(
        OrigemConfiguracaoProcessoEleitoral origemConfiguracaoProcessoEleitoral
    ) {
        return new OrigemConfiguracaoProcessoEleitoralRetrievalDTO(
            origemConfiguracaoProcessoEleitoral.getId(),
            origemConfiguracaoProcessoEleitoral.getNome(),
            origemConfiguracaoProcessoEleitoral.getNomeAbreviado()
        );
    }
}
