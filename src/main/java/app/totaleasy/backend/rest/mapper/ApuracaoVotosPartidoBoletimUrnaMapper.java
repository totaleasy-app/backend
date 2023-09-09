package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.model.ApuracaoVotosPartidoBoletimUrna;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApuracaoVotosPartidoBoletimUrnaMapper {

    private final PartidoMapper partidoMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    private final CargoEleicaoMapper cargoEleicaoMapper;

    public ApuracaoVotosPartidoBoletimUrnaRetrievalDTO toApuracaoVotosPartidoBoletimUrnaRetrievalDTO(ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna) {
        return new ApuracaoVotosPartidoBoletimUrnaRetrievalDTO(
            apuracaoVotosPartidoBoletimUrna.getId(),
            this.partidoMapper.toPartidoRetrievalDTO(apuracaoVotosPartidoBoletimUrna.getPartido()),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(apuracaoVotosPartidoBoletimUrna.getBoletimUrna()),
            this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(apuracaoVotosPartidoBoletimUrna.getCargoEleicao()),
            apuracaoVotosPartidoBoletimUrna.getQuantidadeVotosDeLegenda(),
            apuracaoVotosPartidoBoletimUrna.getTotalVotosApurados()
        );
    }

    public ApuracaoVotosPartidoBoletimUrnaIdDTO toApuracaoVotosPartidoBoletimUrnaIdDTO(ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna) {
        return new ApuracaoVotosPartidoBoletimUrnaIdDTO(
            apuracaoVotosPartidoBoletimUrna.getPartido().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getCargoEleicao().getCargo().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getCargoEleicao().getEleicao().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE()
        );
    }
}
