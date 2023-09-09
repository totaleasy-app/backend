package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import app.totaleasy.backend.rest.service.ApuracaoVotosCandidaturaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-candidaturas")
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaController {

    private final ApuracaoVotosCandidaturaService apuracaoVotosCandidaturaService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCandidaturaRetrievalDTO> findApuracoesVotosCandidaturas() {
        return this.apuracaoVotosCandidaturaService.findAll();
    }
}
