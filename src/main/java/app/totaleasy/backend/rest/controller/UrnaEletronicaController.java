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
import app.totaleasy.backend.rest.mapper.UrnaEletronicaMapper;
import app.totaleasy.backend.rest.service.UrnaEletronicaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/urnas-eletronicas")
@RequiredArgsConstructor
public class UrnaEletronicaController {

    private final UrnaEletronicaService urnaEletronicaService;

    private final UrnaEletronicaMapper urnaEletronicaMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(value = "/{numeroSerie}")
    public ResponseEntity<ApiResponse> findUrnaEletronica(@PathVariable("numeroSerie") Integer numeroSerie) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.urnaEletronicaMapper
                    .toUrnaEletronicaRetrievalDTO(this.urnaEletronicaService.findByNumeroSerie(numeroSerie))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findUrnasEletronicas() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.urnaEletronicaService
                    .findAll()
                    .stream()
                    .map(this.urnaEletronicaMapper::toUrnaEletronicaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{numeroSerie}/boletins-urna")
    public ResponseEntity<ApiResponse> findBoletinsUrna(@PathVariable("numeroSerie") Integer numeroSerie) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.urnaEletronicaService
                    .findBoletinsUrna(numeroSerie)
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
