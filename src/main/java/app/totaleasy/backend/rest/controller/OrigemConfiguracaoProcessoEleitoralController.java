package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.OrigemConfiguracaoProcessoEleitoralMapper;
import app.totaleasy.backend.rest.mapper.ProcessoEleitoralMapper;
import app.totaleasy.backend.rest.service.OrigemConfiguracaoProcessoEleitoralService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/origens-configuracao-processo-eleitoral")
@RequiredArgsConstructor
public class OrigemConfiguracaoProcessoEleitoralController {

    private final OrigemConfiguracaoProcessoEleitoralService origemConfiguracaoProcessoEleitoralService;

    private final OrigemConfiguracaoProcessoEleitoralMapper origemConfiguracaoProcessoEleitoralMapper;

    private final ProcessoEleitoralMapper processoEleitoralMapper;

    @GetMapping(value = "/{nomeAbreviado}")
    public ResponseEntity<ApiResponse> findOrigemConfiguracaoProcessoEleitoral(
        @PathVariable("nomeAbreviado") String nomeAbreviado
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemConfiguracaoProcessoEleitoralMapper.toOrigemConfiguracaoProcessoEleitoralRetrievalDTO(
                    this.origemConfiguracaoProcessoEleitoralService.findByNomeAbreviado(nomeAbreviado)
                )
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findOrigensConfiguracaoProcessoEleitoral() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemConfiguracaoProcessoEleitoralService
                    .findAll()
                    .stream()
                    .map(this.origemConfiguracaoProcessoEleitoralMapper::toOrigemConfiguracaoProcessoEleitoralRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{nomeAbreviado}/processos-eleitorais")
    public ResponseEntity<ApiResponse> findProcessosEleitorais(@PathVariable("nomeAbreviado") String nomeAbreviado) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.origemConfiguracaoProcessoEleitoralService
                    .findProcessosEleitorais(nomeAbreviado)
                    .stream()
                    .map(this.processoEleitoralMapper::toProcessoEleitoralRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
