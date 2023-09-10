package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.EleicaoMapper;
import app.totaleasy.backend.rest.mapper.PleitoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.PleitoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/pleitos")
@RequiredArgsConstructor
public class PleitoController {

    private final PleitoService pleitoService;

    private final PleitoMapper pleitoMapper;

    private final EleicaoMapper eleicaoMapper;

    private final SecaoMapper secaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findPleito(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.pleitoMapper.toPleitoRetrievalDTO(this.pleitoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPleitos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.pleitoService
                    .findAll()
                    .stream()
                    .map(this.pleitoMapper::toPleitoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/secoes")
    public ResponseEntity<ApiResponse> findSecoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.pleitoService
                    .findSecoes(codigoTSE)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/eleicoes")
    public ResponseEntity<ApiResponse> findEleicoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.pleitoService
                    .findEleicoes(codigoTSE)
                    .stream()
                    .map(this.eleicaoMapper::toEleicaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
