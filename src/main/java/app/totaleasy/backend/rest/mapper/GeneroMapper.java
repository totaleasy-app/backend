package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.GeneroRetrievalDTO;
import app.totaleasy.backend.rest.model.Genero;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneroMapper {

    public GeneroRetrievalDTO toGeneroRetrievalDTO(Genero genero) {
        return new GeneroRetrievalDTO(
            genero.getId(),
            genero.getCodigoTSE(),
            genero.getNome()
        );
    }
}
