package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CandidatoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.EstadoCivilRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.EstadoCivilMapper;
import app.totaleasy.backend.rest.service.EstadoCivilService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/estados-civis")
@RequiredArgsConstructor
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    private final EstadoCivilMapper estadoCivilMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public EstadoCivilRetrievalDTO findEstadoCivil(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.estadoCivilMapper.toEstadoCivilRetrievalDTO(this.estadoCivilService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EstadoCivilRetrievalDTO> findEstadosCivis() {
        return this.estadoCivilService
            .findAll()
            .stream()
            .map(this.estadoCivilMapper::toEstadoCivilRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CandidatoRetrievalDTO> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.estadoCivilService
            .findCandidatos(codigoTSE)
            .stream()
            .map(this.candidatoMapper::toCandidatoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
