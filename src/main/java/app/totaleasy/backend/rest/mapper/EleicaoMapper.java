package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.EleicaoRetrievalDTO;
import app.totaleasy.backend.rest.model.Eleicao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EleicaoMapper {

    private final PleitoMapper pleitoMapper;

    public EleicaoRetrievalDTO toEleicaoRetrievalDTO(Eleicao eleicao) {
        return new EleicaoRetrievalDTO(
            eleicao.getId(),
            eleicao.getCodigoTSE(),
            eleicao.getNome(),
            eleicao.getAno(),
            eleicao.getQuantidadeVotosCargosMajoritarios(),
            eleicao.getQuantidadeVotosCargosProporcionais(),
            this.pleitoMapper.toPleitoRetrievalDTO(eleicao.getPleito())
        );
    }
}
