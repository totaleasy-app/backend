package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.CargoRetrievalDTO;
import app.totaleasy.backend.rest.model.Cargo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargoMapper {

    private final TipoCargoMapper tipoCargoMapper;

    public CargoRetrievalDTO toCargoRetrievalDTO(Cargo cargo) {
        return new CargoRetrievalDTO(
            cargo.getId(),
            cargo.getCodigoTSE(),
            cargo.getNome(),
            cargo.getNomeAbreviado(),
            this.tipoCargoMapper.toTipoCargoRetrievalDTO(cargo.getTipo())
        );
    }
}
