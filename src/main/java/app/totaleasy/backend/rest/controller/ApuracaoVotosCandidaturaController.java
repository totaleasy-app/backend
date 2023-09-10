package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.service.ApuracaoVotosCandidaturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-candidaturas")
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaController {

    private final ApuracaoVotosCandidaturaService apuracaoVotosCandidaturaService;

    @GetMapping
    public ResponseEntity<ApiResponse> findApuracoesVotosCandidaturas() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(new ApiResponse(status, this.apuracaoVotosCandidaturaService.findAll()), status);
    }
}
