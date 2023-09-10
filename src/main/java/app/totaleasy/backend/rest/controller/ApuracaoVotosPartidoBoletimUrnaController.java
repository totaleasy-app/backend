package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.ApuracaoVotosPartidoBoletimUrnaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-partidos-boletim-urna")
@RequiredArgsConstructor
public class ApuracaoVotosPartidoBoletimUrnaController {

    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @GetMapping(params = {
        "numeroTSEPartido", "codigoTSECargo", "codigoTSEEleicao",
        "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"
    })
    public ResponseEntity<ApiResponse> findApuracaoVotosPartidoBoletimUrna(
        @Valid ApuracaoVotosPartidoBoletimUrnaIdDTO id
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosPartidoBoletimUrnaMapper
                    .toApuracaoVotosPartidoBoletimUrnaRetrievalDTO(
                        this.apuracaoVotosPartidoBoletimUrnaService.findById(id)
                    )
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findApuracoesVotosPartidosBoletimUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosPartidoBoletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
