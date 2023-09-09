package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.ProcessoEleitoralRetrievalDTO;
import app.totaleasy.backend.rest.model.ProcessoEleitoral;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessoEleitoralMapper {

    private final OrigemConfiguracaoProcessoEleitoralMapper origemConfiguracaoProcessoEleitoralMapper;

    public ProcessoEleitoralRetrievalDTO toProcessoEleitoralRetrievalDTO(ProcessoEleitoral processoEleitoral) {
        return new ProcessoEleitoralRetrievalDTO(
            processoEleitoral.getId(),
            processoEleitoral.getCodigoTSE(),
            processoEleitoral.getNome(),
            this.origemConfiguracaoProcessoEleitoralMapper.toOrigemConfiguracaoProcessoEleitoralRetrievalDTO(
                processoEleitoral.getOrigemConfiguracao()
            )
        );
    }
}
