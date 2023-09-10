package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.build.BoletimUrnaBuildDTO;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaIdDTO;
import app.totaleasy.backend.rest.exception.UsuarioNaoAutenticadoException;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCandidaturaBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCargoBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.QRCodeBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.UsuarioMapper;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.service.BoletimUrnaService;
import app.totaleasy.backend.rest.service.BoletimUrnaUsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/boletins-urna")
@RequiredArgsConstructor
public class BoletimUrnaController {

    private final BoletimUrnaService boletimUrnaService;

    private final BoletimUrnaUsuarioService boletimUrnaUsuarioService;

    private final BoletimUrnaMapper boletimUrnaMapper;

    private final UsuarioMapper usuarioMapper;

    private final QRCodeBoletimUrnaMapper qrCodeBoletimUrnaMapper;

    private final ApuracaoVotosCandidaturaBoletimUrnaMapper apuracaoVotosCandidaturaBoletimUrnaMapper;

    private final ApuracaoVotosCargoBoletimUrnaMapper apuracaoVotosCargoBoletimUrnaMapper;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> buildBoletimUrna(
        @AuthenticationPrincipal Usuario usuario,
        @Valid @RequestBody BoletimUrnaBuildDTO boletimUrnaBuildDTO
    ) {
        if (usuario == null) {
            throw new UsuarioNaoAutenticadoException(
                "O usuário precisa estar autenticado para efetuar uma requisição de construção de boletim de urna."
            );
        }

        this.boletimUrnaUsuarioService.build(boletimUrnaBuildDTO, usuario);

        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(new ApiResponse(status, "O boletim de urna foi criado com sucesso!"), status);
    }

    @GetMapping(params = {"numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    public ResponseEntity<ApiResponse> findBoletimUrna(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(this.boletimUrnaService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findBoletinsUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@Valid BoletimUrnaIdDTO id) {
        this.boletimUrnaService.deleteById(id);

        HttpStatus status = HttpStatus.ACCEPTED;

        return new ResponseEntity<>(new ApiResponse(status), status);
    }

    @GetMapping(value = "/usuarios")
    public ResponseEntity<ApiResponse> findUsuarios(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findUsuarios(id)
                    .stream()
                    .map(this.usuarioMapper::toUsuarioRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/qr-codes")
    public ResponseEntity<ApiResponse> findQRCodes(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findQRCodes(id)
                    .stream()
                    .map(this.qrCodeBoletimUrnaMapper::toQRCodeBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/apuracoes-votos-candidaturas")
    public ResponseEntity<ApiResponse> findApuracoesVotosCandidaturas(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findApuracoesVotosCandidaturas(id)
                    .stream()
                    .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/apuracoes-votos-cargos")
    public ResponseEntity<ApiResponse> findApuracoesVotosCargos(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findApuracoesVotosCargos(id)
                    .stream()
                    .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/apuracoes-votos-partidos")
    public ResponseEntity<ApiResponse> findApuracoesVotosPartidos(@Valid BoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.boletimUrnaService
                    .findApuracoesVotosPartidos(id)
                    .stream()
                    .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
