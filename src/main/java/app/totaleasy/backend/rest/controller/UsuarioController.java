package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.configuration.JwtService;
import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.creation.UsuarioCreationDTO;
import app.totaleasy.backend.rest.dto.request.AuthenticationRequestDTO;
import app.totaleasy.backend.rest.dto.update.UsuarioUpdateDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.PapelMapper;
import app.totaleasy.backend.rest.mapper.UsuarioMapper;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.service.UsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    private final PapelMapper papelMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @GetMapping(value = "/{username}")
    public ResponseEntity<ApiResponse> findUsuario(@PathVariable("username") String username) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.usuarioMapper.toUsuarioRetrievalDTO(this.usuarioService.findByUsername(username))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findUsuarios() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.usuarioService
                    .findAll()
                    .stream()
                    .map(this.usuarioMapper::toUsuarioRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{username}/boletins-urna")
    public ResponseEntity<ApiResponse> findBoletinsUrna(@PathVariable("username") String username) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.usuarioService
                    .findBoletinsUrna(username)
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{username}/papeis")
    public ResponseEntity<ApiResponse> findPapeis(@PathVariable("username") String username) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.usuarioService
                    .findPapeis(username)
                    .stream()
                    .map(this.papelMapper::toPapelRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @PatchMapping(value = "/{username}")
    public ResponseEntity<ApiResponse> update(
        @PathVariable("username") String username,
        @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO
    ) {
        this.usuarioService.updateByUsername(username, usuarioUpdateDTO);

        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(new ApiResponse(status), status);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody UsuarioCreationDTO usuarioCreationDTO) {
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jwtService.generateToken(this.usuarioService.save(usuarioCreationDTO))
            ),
            status
        );
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("username") String username) {
        this.usuarioService.deleteByUsername(username);

        HttpStatus status = HttpStatus.ACCEPTED;

        return new ResponseEntity<>(new ApiResponse(status), status);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<ApiResponse> authenticate(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getUsername(),
                authenticationRequestDTO.getSenha()
            )
        );

        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jwtService
                    .generateToken(this.usuarioService.findByUsername(authenticationRequestDTO.getUsername()))
            ),
            status
        );
    }

    @GetMapping(value = "/current")
    public ResponseEntity<ApiResponse> current(@AuthenticationPrincipal Usuario usuario) {
        HttpStatus status = HttpStatus.OK;

        if (usuario == null) {
            return new ResponseEntity<>(new ApiResponse(status), status);
        }

        return new ResponseEntity<>(
            new ApiResponse(status, this.usuarioMapper.toUsuarioRetrievalDTO(usuario)),
            status
        );
    }
}
