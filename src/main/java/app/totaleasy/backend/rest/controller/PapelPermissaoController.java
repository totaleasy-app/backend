package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.PapelPermissaoIdDTO;
import app.totaleasy.backend.rest.mapper.PapelPermissaoMapper;
import app.totaleasy.backend.rest.service.PapelPermissaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/papeis-permissoes")
@RequiredArgsConstructor
public class PapelPermissaoController {

    private final PapelPermissaoService papelPermissaoService;

    private final PapelPermissaoMapper papelPermissaoMapper;

    @GetMapping(params = {"nomePapel", "nomePermissao"})
    public ResponseEntity<ApiResponse> findPapelPermissao(@Valid PapelPermissaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelPermissaoMapper.toPapelPermissaoRetrievalDTO(this.papelPermissaoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPapeisPermissoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelPermissaoService
                    .findAll()
                    .stream()
                    .map(this.papelPermissaoMapper::toPapelPermissaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
