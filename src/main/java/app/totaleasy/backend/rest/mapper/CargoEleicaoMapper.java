package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.CargoEleicaoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.CargoEleicaoRetrievalDTO;
import app.totaleasy.backend.rest.model.CargoEleicao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargoEleicaoMapper {

    private final CargoMapper cargoMapper;

    private final EleicaoMapper eleicaoMapper;

    public CargoEleicaoRetrievalDTO toCargoEleicaoRetrievalDTO(CargoEleicao cargoEleicao) {
        return new CargoEleicaoRetrievalDTO(
            cargoEleicao.getId(),
            this.cargoMapper.toCargoRetrievalDTO(cargoEleicao.getCargo()),
            this.eleicaoMapper.toEleicaoRetrievalDTO(cargoEleicao.getEleicao())
        );
    }

    public CargoEleicaoIdDTO toCargoEleicaoIdDTO(CargoEleicao cargoEleicao) {
        return new CargoEleicaoIdDTO(
            cargoEleicao.getCargo().getCodigoTSE(),
            cargoEleicao.getEleicao().getCodigoTSE()
        );
    }
}
