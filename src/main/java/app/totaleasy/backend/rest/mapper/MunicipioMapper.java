package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.MunicipioRetrievalDTO;
import app.totaleasy.backend.rest.model.Municipio;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MunicipioMapper {

    private final UFMapper ufMapper;

    public MunicipioRetrievalDTO toMunicipioRetrievalDTO(Municipio municipio) {
        return new MunicipioRetrievalDTO(
            municipio.getId(),
            municipio.getCodigoTSE(),
            municipio.getCodigoIBGE(),
            municipio.getNome(),
            this.ufMapper.toUFRetrievalDTO(municipio.getUF())
        );
    }
}
