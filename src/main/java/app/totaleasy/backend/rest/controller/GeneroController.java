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
import app.totaleasy.backend.rest.mapper.GeneroMapper;
import app.totaleasy.backend.rest.service.GeneroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    private final GeneroMapper generoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findGenero(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.generoMapper.toGeneroRetrievalDTO(this.generoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findGeneros() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.generoService
                    .findAll()
                    .stream()
                    .map(this.generoMapper::toGeneroRetrievalDTO)
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
                this.generoService
                    .findCandidatos(codigoTSE)
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
