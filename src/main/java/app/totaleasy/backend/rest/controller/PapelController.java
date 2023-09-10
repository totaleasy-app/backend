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
import app.totaleasy.backend.rest.mapper.UsuarioMapper;
import app.totaleasy.backend.rest.service.PapelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/papeis")
@RequiredArgsConstructor
public class PapelController {

    private final PapelService papelService;

    private final PapelMapper papelMapper;

    private final PermissaoMapper permissaoMapper;

    private final UsuarioMapper usuarioMapper;

    @GetMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> findPapel(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelMapper.toPapelRetrievalDTO(this.papelService.findByNome(nome))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPapeis() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelService
                    .findAll()
                    .stream()
                    .map(this.papelMapper::toPapelRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}/permissoes")
    public ResponseEntity<ApiResponse> findPermissoes(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelService
                    .findPermissoes(nome)
                    .stream()
                    .map(this.permissaoMapper::toPermissaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}/usuarios")
    public ResponseEntity<ApiResponse> findUsuarios(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelService
                    .findUsuarios(nome)
                    .stream()
                    .map(this.usuarioMapper::toUsuarioRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
