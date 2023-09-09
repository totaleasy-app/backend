package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.CorRacaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.CorRacaMapper;
import app.totaleasy.backend.rest.service.CorRacaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/cores-racas")
@RequiredArgsConstructor
public class CorRacaController {

    private final CorRacaService corRacaService;

    private final CorRacaMapper corRacaMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public CorRacaRetrievalDTO findCorRaca(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.corRacaMapper.toCorRacaRetrievalDTO(this.corRacaService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CorRacaRetrievalDTO> findCoresRacas() {
        return this.corRacaService
            .findAll()
            .stream()
            .map(this.corRacaMapper::toCorRacaRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CandidatoRetrievalDTO> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.corRacaService
            .findCandidatos(codigoTSE)
            .stream()
            .map(this.candidatoMapper::toCandidatoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
