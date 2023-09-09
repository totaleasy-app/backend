package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.SecaoPleitoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.SecaoPleitoRetrievalDTO;
import app.totaleasy.backend.rest.model.SecaoPleito;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecaoPleitoMapper {

    private final SecaoMapper secaoMapper;

    private final PleitoMapper pleitoMapper;

    public SecaoPleitoRetrievalDTO toSecaoPleitoRetrievalDTO(SecaoPleito secaoPleito) {
        return new SecaoPleitoRetrievalDTO(
            secaoPleito.getId(),
            this.secaoMapper.toSecaoRetrievalDTO(secaoPleito.getSecao()),
            this.pleitoMapper.toPleitoRetrievalDTO(secaoPleito.getPleito()),
            secaoPleito.getQuantidadeEleitoresAptos(),
            secaoPleito.getQuantidadeEleitoresFaltosos(),
            secaoPleito.getQuantidadeEleitoresComparecentes(),
            secaoPleito.getQuantidadeEleitoresHabilitadosPorAnoNascimento()
        );
    }

    public SecaoPleitoIdDTO toSecaoPleitoIdDTO(SecaoPleito secaoPleito) {
        return new SecaoPleitoIdDTO(
            secaoPleito.getSecao().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getUF().getSigla(),
            secaoPleito.getPleito().getCodigoTSE()
        );
    }
}
