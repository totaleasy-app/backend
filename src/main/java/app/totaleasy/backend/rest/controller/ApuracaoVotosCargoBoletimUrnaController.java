package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.ApuracaoVotosCargoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCargoBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.ApuracaoVotosCargoBoletimUrnaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-cargos-boletim-urna")
@RequiredArgsConstructor
public class ApuracaoVotosCargoBoletimUrnaController {

    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;

    private final ApuracaoVotosCargoBoletimUrnaMapper apuracaoVotosCargoBoletimUrnaMapper;

    @GetMapping(params = {
        "codigoTSECargo", "codigoTSEEleicao", "numeroTSESecao",
        "numeroTSEZona", "siglaUF", "codigoTSEPleito"
    })
    public ResponseEntity<ApiResponse> findApuracaoVotosCargoBoletimUrna(@Valid ApuracaoVotosCargoBoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosCargoBoletimUrnaMapper
                    .toApuracaoVotosCargoBoletimUrnaRetrievalDTO(this.apuracaoVotosCargoBoletimUrnaService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findApuracoesVotosCargosBoletimUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosCargoBoletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
