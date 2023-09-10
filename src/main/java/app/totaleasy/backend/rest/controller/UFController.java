package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.MunicipioMapper;
import app.totaleasy.backend.rest.mapper.UFMapper;
import app.totaleasy.backend.rest.mapper.ZonaMapper;
import app.totaleasy.backend.rest.service.UFService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/ufs")
@RequiredArgsConstructor
public class UFController {

    private final UFService ufService;

    private final UFMapper ufMapper;

    private final ZonaMapper zonaMapper;

    private final MunicipioMapper municipioMapper;

    @GetMapping(value = "/{sigla}")
    public ResponseEntity<ApiResponse> findUF(@PathVariable("sigla") String sigla) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(status, this.ufMapper.toUFRetrievalDTO(this.ufService.findBySigla(sigla))),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findUFs() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.ufService
                    .findAll()
                    .stream()
                    .map(this.ufMapper::toUFRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{sigla}/zonas")
    public ResponseEntity<ApiResponse> findZonas(@PathVariable("sigla") String sigla) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.ufService
                    .findZonas(sigla)
                    .stream()
                    .map(this.zonaMapper::toZonaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{sigla}/municipios")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findMunicipios(@PathVariable("sigla") String sigla) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.ufService
                    .findMunicipios(sigla)
                    .stream()
                    .map(this.municipioMapper::toMunicipioRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
