package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.CandidaturaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.CandidaturaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCandidaturaBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.CandidaturaMapper;
import app.totaleasy.backend.rest.service.CandidaturaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/candidaturas")
@RequiredArgsConstructor
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    private final CandidaturaMapper candidaturaMapper;

    private final ApuracaoVotosCandidaturaBoletimUrnaMapper apuracaoVotosCandidaturaBoletimUrnaMapper;

    @GetMapping(params = {"numeroTSECandidato", "codigoTSECargo", "codigoTSEEleicao"})
    @ResponseStatus(value = HttpStatus.OK)
    public CandidaturaRetrievalDTO findCandidatura(@Valid CandidaturaIdDTO id) {
        return this.candidaturaMapper.toCandidaturaRetrievalDTO(this.candidaturaService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CandidaturaRetrievalDTO> findCandidaturas() {
        return this.candidaturaService
            .findAll()
            .stream()
            .map(this.candidaturaMapper::toCandidaturaRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/apuracoes-votos-boletim-urna")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO> findApuracoesVotosBoletimUrna(@Valid CandidaturaIdDTO id) {
        return this.candidaturaService
            .findApuracoesVotosBoletimUrna(id)
            .stream()
            .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
