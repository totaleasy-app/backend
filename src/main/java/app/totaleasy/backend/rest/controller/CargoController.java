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
import app.totaleasy.backend.rest.service.CargoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    private final CargoMapper cargoMapper;

    private final EleicaoMapper eleicaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findCargo(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoMapper.toCargoRetrievalDTO(this.cargoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findCargos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoService
                    .findAll()
                    .stream()
                    .map(this.cargoMapper::toCargoRetrievalDTO)
                    .toList()
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
                this.cargoService
                    .findEleicoes(codigoTSE)
                    .stream()
                    .map(this.eleicaoMapper::toEleicaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
