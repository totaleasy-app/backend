package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.PapelMapper;
import app.totaleasy.backend.rest.mapper.PermissaoMapper;
import app.totaleasy.backend.rest.service.PermissaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoService permissaoService;

    private final PermissaoMapper permissaoMapper;

    private final PapelMapper papelMapper;

    @GetMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> findPermissao(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.permissaoMapper.toPermissaoRetrievalDTO(this.permissaoService.findByNome(nome))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPermissoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.permissaoService
                    .findAll()
                    .stream()
                    .map(this.permissaoMapper::toPermissaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}/papeis")
    public ResponseEntity<ApiResponse> findPapeis(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.permissaoService
                    .findPapeis(nome)
                    .stream()
                    .map(this.papelMapper::toPapelRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
