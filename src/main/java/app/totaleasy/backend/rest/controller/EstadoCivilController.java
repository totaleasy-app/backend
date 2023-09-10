package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.CandidatoMapper;
import app.totaleasy.backend.rest.mapper.EstadoCivilMapper;
import app.totaleasy.backend.rest.service.EstadoCivilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/estados-civis")
@RequiredArgsConstructor
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    private final EstadoCivilMapper estadoCivilMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findEstadoCivil(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.estadoCivilMapper.toEstadoCivilRetrievalDTO(this.estadoCivilService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findEstadosCivis() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.estadoCivilService
                    .findAll()
                    .stream()
                    .map(this.estadoCivilMapper::toEstadoCivilRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/candidatos")
    public ResponseEntity<ApiResponse> findCandidatos(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.estadoCivilService
                    .findCandidatos(codigoTSE)
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
