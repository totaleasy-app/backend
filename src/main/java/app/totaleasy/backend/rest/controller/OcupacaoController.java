package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.OcupacaoRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.OcupacaoMapper;
import app.totaleasy.backend.rest.service.OcupacaoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/ocupacoes")
@RequiredArgsConstructor
public class OcupacaoController {

    private final OcupacaoService ocupacaoService;

    private final OcupacaoMapper ocupacaoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public OcupacaoRetrievalDTO findOcupacao(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.ocupacaoMapper.toOcupacaoRetrievalDTO(this.ocupacaoService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OcupacaoRetrievalDTO> findOcupacoes() {
        return this.ocupacaoService
            .findAll()
            .stream()
            .map(this.ocupacaoMapper::toOcupacaoRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CandidatoRetrievalDTO> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.ocupacaoService
            .findCandidatos(codigoTSE)
            .stream()
            .map(this.candidatoMapper::toCandidatoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
