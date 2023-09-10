package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.OrigemBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.OrigemBoletimUrnaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/origens-boletim-urna")
@RequiredArgsConstructor
public class OrigemBoletimUrnaController {

    private final OrigemBoletimUrnaService origemBoletimUrnaService;

    private final OrigemBoletimUrnaMapper origemBoletimUrnaMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(value = "/{nomeAbreviado}")
    public ResponseEntity<ApiResponse> findOrigemBoletimUrna(@PathVariable("nomeAbreviado") String nomeAbreviado) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemBoletimUrnaMapper.toOrigemBoletimUrnaRetrievalDTO(
                    this.origemBoletimUrnaService.findByNomeAbreviado(nomeAbreviado)
                )
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findOrigensBoletimUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemBoletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.origemBoletimUrnaMapper::toOrigemBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{nomeAbreviado}/boletins-urna")
    public ResponseEntity<ApiResponse> findBoletinsUrna(@PathVariable("nomeAbreviado") String nomeAbreviado) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemBoletimUrnaService
                    .findBoletinsUrna(nomeAbreviado)
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
