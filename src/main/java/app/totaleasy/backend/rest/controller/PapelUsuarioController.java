package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.PapelUsuarioIdDTO;
import app.totaleasy.backend.rest.mapper.PapelUsuarioMapper;
import app.totaleasy.backend.rest.service.PapelUsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/papeis-usuarios")
@RequiredArgsConstructor
public class PapelUsuarioController {

    private final PapelUsuarioService papelUsuarioService;

    private final PapelUsuarioMapper papelUsuarioMapper;

    @GetMapping(params = {"username", "nomePapel"})
    public ResponseEntity<ApiResponse> findPapelUsuario(@Valid PapelUsuarioIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelUsuarioMapper.toPapelUsuarioRetrievalDTO(this.papelUsuarioService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findPapeisUsuarios() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.papelUsuarioService
                    .findAll()
                    .stream()
                    .map(this.papelUsuarioMapper::toPapelUsuarioRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
