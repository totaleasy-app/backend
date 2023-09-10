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
import app.totaleasy.backend.rest.mapper.GrauInstrucaoMapper;
import app.totaleasy.backend.rest.service.GrauInstrucaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/graus-instrucao")
@RequiredArgsConstructor
public class GrauInstrucaoController {

    private final GrauInstrucaoService grauInstrucaoService;

    private final GrauInstrucaoMapper grauInstrucaoMapper;

    private final CandidatoMapper candidatoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findGrauInstrucao(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.grauInstrucaoMapper.toGrauInstrucaoRetrievalDTO(this.grauInstrucaoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findGrausInstrucao() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.grauInstrucaoService
                    .findAll()
                    .stream()
                    .map(this.grauInstrucaoMapper::toGrauInstrucaoRetrievalDTO)
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
                this.grauInstrucaoService
                    .findCandidatos(codigoTSE)
                    .stream()
                    .map(this.candidatoMapper::toCandidatoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
