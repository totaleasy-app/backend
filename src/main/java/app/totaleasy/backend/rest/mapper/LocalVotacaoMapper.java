package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.LocalVotacaoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import app.totaleasy.backend.rest.model.LocalVotacao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalVotacaoMapper {

    private final ZonaMapper zonaMapper;

    private final MunicipioMapper municipioMapper;

    public LocalVotacaoRetrievalDTO toLocalVotacaoRetrievalDTO(LocalVotacao localVotacao) {
        return new LocalVotacaoRetrievalDTO(
            localVotacao.getId(),
            localVotacao.getNumeroTSE(),
            localVotacao.getNome(),
            this.zonaMapper.toZonaRetrievalDTO(localVotacao.getZona()),
            this.municipioMapper.toMunicipioRetrievalDTO(localVotacao.getMunicipio())
        );
    }

    public LocalVotacaoIdDTO toLocalVotacaoIdDTO(LocalVotacao localVotacao) {
        return new LocalVotacaoIdDTO(
            localVotacao.getNumeroTSE(),
            localVotacao.getZona().getNumeroTSE(),
            localVotacao.getZona().getUF().getSigla()
        );
    }
}
