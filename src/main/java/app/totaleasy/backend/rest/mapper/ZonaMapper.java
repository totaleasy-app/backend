package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.ZonaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ZonaRetrievalDTO;
import app.totaleasy.backend.rest.model.Zona;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZonaMapper {

    private final UFMapper ufMapper;

    public ZonaRetrievalDTO toZonaRetrievalDTO(Zona zona) {
        return new ZonaRetrievalDTO(
            zona.getId(),
            zona.getNumeroTSE(),
            this.ufMapper.toUFRetrievalDTO(zona.getUF())
        );
    }

    public ZonaIdDTO toZonaIdDTO(Zona zona) {
        return new ZonaIdDTO(
            zona.getNumeroTSE(),
            zona.getUF().getSigla()
        );
    }
}
