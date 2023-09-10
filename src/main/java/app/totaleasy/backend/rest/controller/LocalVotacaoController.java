package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.LocalVotacaoIdDTO;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.LocalVotacaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/locais-votacao")
@RequiredArgsConstructor
public class LocalVotacaoController {

    private final LocalVotacaoService localVotacaoService;

    private final LocalVotacaoMapper localVotacaoMapper;

    private final SecaoMapper secaoMapper;

    @GetMapping(params = {"numeroTSELocalVotacao", "numeroTSEZona", "siglaUF"})
    public ResponseEntity<ApiResponse> findLocalVotacao(@Valid LocalVotacaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.localVotacaoMapper.toLocalVotacaoRetrievalDTO(this.localVotacaoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findLocaisVotacao() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.localVotacaoService
                    .findAll()
                    .stream()
                    .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/secoes")
    public ResponseEntity<ApiResponse> findSecoes(@Valid LocalVotacaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.localVotacaoService
                    .findSecoes(id)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
