package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.model.Candidato;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandidatoMapper {

    private final GeneroMapper generoMapper;

    private final CorRacaMapper corRacaMapper;

    private final GrauInstrucaoMapper grauInstrucaoMapper;

    private final EstadoCivilMapper estadoCivilMapper;

    private final OcupacaoMapper ocupacaoMapper;

    public CandidatoRetrievalDTO toCandidatoRetrievalDTO(Candidato candidato) {
        return new CandidatoRetrievalDTO(
            candidato.getId(),
            candidato.getNumeroTSE(),
            candidato.getCodigoTSE(),
            candidato.getNomeCompleto(),
            candidato.getNomeUrna(),
            candidato.getNomeSocial(),
            candidato.getNumeroCPF(),
            candidato.getNumeroTituloEleitoral(),
            candidato.getDataNascimento(),
            this.generoMapper.toGeneroRetrievalDTO(candidato.getGenero()),
            this.corRacaMapper.toCorRacaRetrievalDTO(candidato.getCorRaca()),
            this.grauInstrucaoMapper.toGrauInstrucaoRetrievalDTO(candidato.getGrauInstrucao()),
            this.estadoCivilMapper.toEstadoCivilRetrievalDTO(candidato.getEstadoCivil()),
            this.ocupacaoMapper.toOcupacaoRetrievalDTO(candidato.getOcupacao())
        );
    }
}
