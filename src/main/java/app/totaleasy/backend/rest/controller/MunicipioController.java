package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.MunicipioMapper;
import app.totaleasy.backend.rest.service.MunicipioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    private final MunicipioMapper municipioMapper;

    private final LocalVotacaoMapper localVotacaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findMunicipio(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.municipioMapper.toMunicipioRetrievalDTO(this.municipioService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findMunicipios() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.municipioService
                    .findAll()
                    .stream()
                    .map(this.municipioMapper::toMunicipioRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/locais-votacao")
    public ResponseEntity<ApiResponse> findLocaisVotacao(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.municipioService
                    .findLocaisVotacao(codigoTSE)
                    .stream()
                    .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
