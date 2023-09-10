package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.SecaoIdDTO;
import app.totaleasy.backend.rest.mapper.PleitoMapper;
import app.totaleasy.backend.rest.mapper.ProcessoEleitoralMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.SecaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/secoes")
@RequiredArgsConstructor
public class SecaoController {

    private final SecaoService secaoService;

    private final SecaoMapper secaoMapper;

    private final PleitoMapper pleitoMapper;

    private final ProcessoEleitoralMapper processoEleitoralMapper;

    @GetMapping(params = {"numeroTSESecao", "numeroTSEZona", "siglaUF"})
    public ResponseEntity<ApiResponse> findSecao(@Valid SecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(status, this.secaoMapper.toSecaoRetrievalDTO(this.secaoService.findById(id))),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findSecoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoService
                    .findAll()
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/pleitos")
    public ResponseEntity<ApiResponse> findPleitos(@Valid SecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoService
                    .findPleitos(id)
                    .stream()
                    .map(this.pleitoMapper::toPleitoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/processos-eleitorais")
    public ResponseEntity<ApiResponse> findProcessosEleitorais(@Valid SecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoService
                    .findProcessosEleitorais(id)
                    .stream()
                    .map(this.processoEleitoralMapper::toProcessoEleitoralRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/principais")
    public ResponseEntity<ApiResponse> findSecoesPrincipais(@Valid SecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoService
                    .findSecoesPrincipais(id)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/agregadas")
    public ResponseEntity<ApiResponse> findSecoesAgregadas(@Valid SecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoService
                    .findSecoesAgregadas(id)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
