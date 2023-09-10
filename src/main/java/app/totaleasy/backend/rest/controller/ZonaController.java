package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.ZonaIdDTO;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.mapper.ZonaMapper;
import app.totaleasy.backend.rest.service.ZonaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/zonas")
@RequiredArgsConstructor
public class ZonaController {

    private final ZonaService zonaService;

    private final ZonaMapper zonaMapper;

    private final SecaoMapper secaoMapper;

    private final LocalVotacaoMapper localVotacaoMapper;

    @GetMapping(params = {"numeroTSEZona", "siglaUF"})
    public ResponseEntity<ApiResponse> findZona(@Valid ZonaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.zonaMapper.toZonaRetrievalDTO(this.zonaService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findZonas() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.zonaService
                    .findAll()
                    .stream()
                    .map(this.zonaMapper::toZonaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/secoes")
    public ResponseEntity<ApiResponse> findSecoes(@Valid ZonaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.zonaService
                    .findSecoes(id)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/locais-votacao")
    public ResponseEntity<ApiResponse> findLocaisVotacao(@Valid ZonaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.zonaService
                    .findLocaisVotacao(id)
                    .stream()
                    .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
