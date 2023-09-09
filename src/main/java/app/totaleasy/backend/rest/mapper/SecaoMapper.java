package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.SecaoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.SecaoRetrievalDTO;
import app.totaleasy.backend.rest.model.Secao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecaoMapper {

    private final ZonaMapper zonaMapper;

    public SecaoRetrievalDTO toSecaoRetrievalDTO(Secao secao) {
        return new SecaoRetrievalDTO(
            secao.getId(),
            secao.getNumeroTSE(),
            this.zonaMapper.toZonaRetrievalDTO(secao.getZona())
        );
    }

    public SecaoIdDTO toSecaoIdDTO(Secao secao) {
        return new SecaoIdDTO(
            secao.getNumeroTSE(),
            secao.getZona().getNumeroTSE(),
            secao.getZona().getUF().getSigla()
        );
    }
}
