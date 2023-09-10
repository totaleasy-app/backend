package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.CargoMapper;
import app.totaleasy.backend.rest.mapper.EleicaoMapper;
import app.totaleasy.backend.rest.service.EleicaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/eleicoes")
@RequiredArgsConstructor
public class EleicaoController {

    private final EleicaoService eleicaoService;

    private final EleicaoMapper eleicaoMapper;

    private final CargoMapper cargoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findEleicao(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.eleicaoMapper.toEleicaoRetrievalDTO(this.eleicaoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findEleicoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.eleicaoService
                    .findAll()
                    .stream()
                    .map(this.eleicaoMapper::toEleicaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/cargos")
    public ResponseEntity<ApiResponse> findCargos(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.eleicaoService
                    .findCargos(codigoTSE)
                    .stream()
                    .map(this.cargoMapper::toCargoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
