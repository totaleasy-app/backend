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
import app.totaleasy.backend.rest.mapper.OcupacaoMapper;
import app.totaleasy.backend.rest.service.OcupacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/ocupacoes")
@RequiredArgsConstructor
public class OcupacaoController {

    private final OcupacaoService ocupacaoService;

    private final OcupacaoMapper ocupacaoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findOcupacao(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.ocupacaoMapper.toOcupacaoRetrievalDTO(this.ocupacaoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findOcupacoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.ocupacaoService
                    .findAll()
                    .stream()
                    .map(this.ocupacaoMapper::toOcupacaoRetrievalDTO)
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
                this.ocupacaoService
                    .findCandidatos(codigoTSE)
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
