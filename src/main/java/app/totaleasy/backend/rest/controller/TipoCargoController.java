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
import app.totaleasy.backend.rest.mapper.TipoCargoMapper;
import app.totaleasy.backend.rest.service.TipoCargoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/tipos-cargo")
@RequiredArgsConstructor
public class TipoCargoController {

    private final TipoCargoService tipoCargoService;

    private final TipoCargoMapper tipoCargoMapper;

    private final CargoMapper cargoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findTipoCargo(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.tipoCargoMapper.toTipoCargoRetrievalDTO(this.tipoCargoService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findTiposCargo() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.tipoCargoService
                    .findAll()
                    .stream()
                    .map(this.tipoCargoMapper::toTipoCargoRetrievalDTO)
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
                this.tipoCargoService
                    .findCargos(codigoTSE)
                    .stream()
                    .map(this.cargoMapper::toCargoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
