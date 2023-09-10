package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.SecaoProcessoEleitoralIdDTO;
import app.totaleasy.backend.rest.mapper.SecaoProcessoEleitoralMapper;
import app.totaleasy.backend.rest.service.SecaoProcessoEleitoralService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/secoes-processos-eleitorais")
@RequiredArgsConstructor
public class SecaoProcessoEleitoralController {

    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    private final SecaoProcessoEleitoralMapper secaoProcessoEleitoralMapper;

    @GetMapping(params = {"numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEProcessoEleitoral"})
    public ResponseEntity<ApiResponse> findSecaoProcessoEleitoral(@Valid SecaoProcessoEleitoralIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoProcessoEleitoralMapper
                    .toSecaoProcessoEleitoralRetrievalDTO(this.secaoProcessoEleitoralService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findSecoesProcessosEleitorais() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoProcessoEleitoralService
                    .findAll()
                    .stream()
                    .map(this.secaoProcessoEleitoralMapper::toSecaoProcessoEleitoralRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
