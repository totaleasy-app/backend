package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.configuration.JwtService;
import app.totaleasy.backend.rest.dto.creation.UsuarioCreationDTO;
import app.totaleasy.backend.rest.dto.request.AuthenticationRequestDTO;
import app.totaleasy.backend.rest.dto.response.ApiEmptyResponse;
import app.totaleasy.backend.rest.dto.response.ApiResponse;
import app.totaleasy.backend.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.PapelRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.UsuarioRetrievalDTO;
import app.totaleasy.backend.rest.dto.update.UsuarioUpdateDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.PapelMapper;
import app.totaleasy.backend.rest.mapper.UsuarioMapper;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.service.UsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ResponseStatus(value = HttpStatus.OK)
    public UsuarioRetrievalDTO findUsuario(@PathVariable("username") String username) {
        return this.usuarioMapper.toUsuarioRetrievalDTO(this.usuarioService.findByUsername(username));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Set<UsuarioRetrievalDTO> findUsuarios() {
        return this.usuarioService
            .findAll()
            .stream()
            .map(this.usuarioMapper::toUsuarioRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/{username}/boletins-urna")
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoletimUrnaRetrievalDTO> findBoletinsUrna(@PathVariable("username") String username) {
        return this.usuarioService
            .findBoletinsUrna(username)
            .stream()
            .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{username}/papeis")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<PapelRetrievalDTO> findPapeis(@PathVariable("username") String username) {
        return this.usuarioService
            .findPapeis(username)
            .stream()
            .map(this.papelMapper::toPapelRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @PatchMapping(value = "/{username}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void update(
        @PathVariable("username") String username,
        @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO
    ) {
        this.usuarioService.updateByUsername(username, usuarioUpdateDTO);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ApiResponse<String> create(@Valid @RequestBody UsuarioCreationDTO usuarioCreationDTO) {
        return new ApiResponse<>(
            this.jwtService.generateToken(
                this.usuarioService.save(usuarioCreationDTO)
            ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(value = "/{username}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("username") String username) {
        this.usuarioService.deleteByUsername(username);
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(value = HttpStatus.OK)
    public ApiResponse<String> authenticate(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getUsername(),
                authenticationRequestDTO.getSenha()
            )
        );

        return new ApiResponse<>(
            this.jwtService.generateToken(
                this.usuarioService.findByUsername(authenticationRequestDTO.getUsername())
            ),
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/current")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Object> current(@AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return new ResponseEntity<>(new ApiEmptyResponse(), HttpStatus.OK);
        }

        return new ResponseEntity<>(
            this.usuarioMapper.toUsuarioRetrievalDTO(usuario),
            HttpStatus.OK
        );
    }
}
