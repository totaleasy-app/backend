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
import app.totaleasy.backend.rest.mapper.CandidaturaMapper;
import app.totaleasy.backend.rest.service.CandidatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    private final CandidatoMapper candidatoMapper;

    private final CandidaturaMapper candidaturaMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findCandidato(@PathVariable("codigoTSE") String codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.candidatoMapper.toCandidatoRetrievalDTO(this.candidatoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findCandidatos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.candidatoService
                    .findAll()
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/candidaturas")
    public ResponseEntity<ApiResponse> findCandidaturas(@PathVariable("codigoTSE") String codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.candidatoService
                    .findCandidaturas(codigoTSE)
                    .stream()
                    .map(this.candidaturaMapper::toCandidaturaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
