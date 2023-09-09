package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.GrauInstrucaoRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.GrauInstrucaoMapper;
import app.totaleasy.backend.rest.service.GrauInstrucaoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/graus-instrucao")
@RequiredArgsConstructor
public class GrauInstrucaoController {

    private final GrauInstrucaoService grauInstrucaoService;

    private final GrauInstrucaoMapper grauInstrucaoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public GrauInstrucaoRetrievalDTO findGrauInstrucao(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.grauInstrucaoMapper.toGrauInstrucaoRetrievalDTO(this.grauInstrucaoService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<GrauInstrucaoRetrievalDTO> findGrausInstrucao() {
        return this.grauInstrucaoService
            .findAll()
            .stream()
            .map(this.grauInstrucaoMapper::toGrauInstrucaoRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CandidatoRetrievalDTO> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.grauInstrucaoService
            .findCandidatos(codigoTSE)
            .stream()
            .map(this.candidatoMapper::toCandidatoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
