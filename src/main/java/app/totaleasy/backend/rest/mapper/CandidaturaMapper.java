package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.service.CandidatoService;
import app.totaleasy.backend.rest.dto.id.CandidaturaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.CandidaturaRetrievalDTO;
import app.totaleasy.backend.rest.model.Candidatura;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandidaturaMapper {

    private final CandidatoMapper candidatoMapper;

    private final CargoEleicaoMapper cargoEleicaoMapper;

    private final PartidoMapper partidoMapper;

    private final CandidatoService candidatoService;

    public CandidaturaRetrievalDTO toCandidaturaRetrievalDTO(Candidatura candidatura) {
        return new CandidaturaRetrievalDTO(
            candidatura.getId(),
            this.candidatoMapper.toCandidatoRetrievalDTO(
                this.candidatoService.findByCodigoTSE(candidatura.getCandidato().getCodigoTSE())
            ),
            this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(candidatura.getCargoEleicao()),
            this.partidoMapper.toPartidoRetrievalDTO(candidatura.getPartido())
        );
    }

    public CandidaturaIdDTO toCandidaturaIdDTO(Candidatura candidatura) {
        return new CandidaturaIdDTO(
            candidatura.getCandidato().getNumeroTSE(),
            candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
            candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
        );
    }
}
