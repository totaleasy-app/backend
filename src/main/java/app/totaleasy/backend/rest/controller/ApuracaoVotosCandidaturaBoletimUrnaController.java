package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.ApuracaoVotosCandidaturaBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCandidaturaBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.ApuracaoVotosCandidaturaBoletimUrnaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-candidaturas-boletim-urna")
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaBoletimUrnaController {

    private final ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService;

    private final ApuracaoVotosCandidaturaBoletimUrnaMapper apuracaoVotosCandidaturaBoletimUrnaMapper;

    @GetMapping(params = {
        "numeroTSECandidato", "codigoTSECargo", "codigoTSEEleicao",
        "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"
    })
    public ResponseEntity<ApiResponse> findApuracaoVotosCandidaturaBoletimUrna(
        @Valid ApuracaoVotosCandidaturaBoletimUrnaIdDTO id
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosCandidaturaBoletimUrnaMapper
                    .toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO(
                        this.apuracaoVotosCandidaturaBoletimUrnaService.findById(id)
                    )
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findApuracoesVotosCandidaturasBoletimUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.apuracaoVotosCandidaturaBoletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
