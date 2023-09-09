package app.totaleasy.backend.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.id.ApuracaoVotosCandidaturaBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO;
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
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO findApuracaoVotosCandidaturaBoletimUrna(
        @Valid ApuracaoVotosCandidaturaBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosCandidaturaBoletimUrnaMapper
            .toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO(
                this.apuracaoVotosCandidaturaBoletimUrnaService.findById(id)
            );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO> findApuracoesVotosCandidaturasBoletimUrna() {
        return this.apuracaoVotosCandidaturaBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
            .toList();
    }
}
