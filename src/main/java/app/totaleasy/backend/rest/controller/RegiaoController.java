package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.RegiaoMapper;
import app.totaleasy.backend.rest.mapper.UFMapper;
import app.totaleasy.backend.rest.service.RegiaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/regioes")
@RequiredArgsConstructor
public class RegiaoController {

    private final RegiaoService regiaoService;

    private final RegiaoMapper regiaoMapper;

    private final UFMapper ufMapper;

    @GetMapping(value = "/{sigla}")
    public ResponseEntity<ApiResponse> findRegiao(@PathVariable("sigla") String sigla) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(status, this.regiaoMapper.toRegiaoRetrievalDTO(this.regiaoService.findBySigla(sigla))),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findRegioes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.regiaoService
                    .findAll()
                    .stream()
                    .map(this.regiaoMapper::toRegiaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{sigla}/ufs")
    public ResponseEntity<ApiResponse> findUFs(@PathVariable("sigla") String sigla) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.regiaoService
                    .findUFs(sigla)
                    .stream()
                    .map(this.ufMapper::toUFRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
