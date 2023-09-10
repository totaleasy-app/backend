package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaUsuarioIdDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaUsuarioMapper;
import app.totaleasy.backend.rest.service.BoletimUrnaUsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/boletins-urna-usuarios")
@RequiredArgsConstructor
public class BoletimUrnaUsuarioController {

    private final BoletimUrnaUsuarioService boletimUrnaUsuarioService;

    private final BoletimUrnaUsuarioMapper boletimUrnaUsuarioMapper;

    @GetMapping(params = {"username", "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    public ResponseEntity<ApiResponse> findBoletimUrnaUsuario(@Valid BoletimUrnaUsuarioIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaUsuarioMapper.toBoletimUrnaUsuarioRetrievalDTO(
                    this.boletimUrnaUsuarioService.findById(id)
                )
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findBoletinsUrnaUsuarios() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaUsuarioService
                    .findAll()
                    .stream()
                    .map(this.boletimUrnaUsuarioMapper::toBoletimUrnaUsuarioRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
