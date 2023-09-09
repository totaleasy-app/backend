package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.GeneroRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.GeneroMapper;
import app.totaleasy.backend.rest.service.GeneroService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    private final GeneroMapper generoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public GeneroRetrievalDTO findGenero(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.generoMapper.toGeneroRetrievalDTO(this.generoService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<GeneroRetrievalDTO> findGeneros() {
        return this.generoService
            .findAll()
            .stream()
            .map(this.generoMapper::toGeneroRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CandidatoRetrievalDTO> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.generoService
            .findCandidatos(codigoTSE)
            .stream()
            .map(this.candidatoMapper::toCandidatoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
