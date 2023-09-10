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
import app.totaleasy.backend.rest.mapper.CorRacaMapper;
import app.totaleasy.backend.rest.service.CorRacaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/cores-racas")
@RequiredArgsConstructor
public class CorRacaController {

    private final CorRacaService corRacaService;

    private final CorRacaMapper corRacaMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findCorRaca(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.corRacaMapper.toCorRacaRetrievalDTO(this.corRacaService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findCoresRacas() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.corRacaService
                    .findAll()
                    .stream()
                    .map(this.corRacaMapper::toCorRacaRetrievalDTO)
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
                this.corRacaService
                    .findCandidatos(codigoTSE)
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
