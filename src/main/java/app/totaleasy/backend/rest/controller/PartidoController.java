package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.CandidaturaMapper;
import app.totaleasy.backend.rest.mapper.PartidoMapper;
import app.totaleasy.backend.rest.service.PartidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/partidos")
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService partidoService;

    private final PartidoMapper partidoMapper;

    private final CandidaturaMapper candidaturaMapper;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @GetMapping(value = "/{numeroTSE}")
    public ResponseEntity<ApiResponse> findPartido(@PathVariable("numeroTSE") Integer numeroTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.partidoMapper.toPartidoRetrievalDTO(this.partidoService.findByNumeroTSE(numeroTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPartidos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.partidoService
                    .findAll()
                    .stream()
                    .map(this.partidoMapper::toPartidoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{numeroTSE}/candidaturas")
    public ResponseEntity<ApiResponse> findCandidaturas(@PathVariable("numeroTSE") Integer numeroTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.partidoService
                    .findCandidaturas(numeroTSE)
                    .stream()
                    .map(this.candidaturaMapper::toCandidaturaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{numeroTSE}/apuracoes-votos-boletim-urna")
    public ResponseEntity<ApiResponse> findApuracoesVotosBoletimUrna(@PathVariable("numeroTSE") Integer numeroTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.partidoService
                    .findApuracoesVotosBoletimUrna(numeroTSE)
                    .stream()
                    .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
