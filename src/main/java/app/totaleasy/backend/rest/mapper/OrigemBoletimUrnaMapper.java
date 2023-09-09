package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.OrigemBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.model.OrigemBoletimUrna;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrigemBoletimUrnaMapper {

    public OrigemBoletimUrnaRetrievalDTO toOrigemBoletimUrnaRetrievalDTO(OrigemBoletimUrna origemBoletimUrna) {
        return new OrigemBoletimUrnaRetrievalDTO(
            origemBoletimUrna.getId(),
            origemBoletimUrna.getNome(),
            origemBoletimUrna.getNomeAbreviado()
        );
    }
}
