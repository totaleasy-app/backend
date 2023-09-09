package app.totaleasy.backend.rest.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.build.BoletimUrnaBuildDTO;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.response.ApiResponse;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCargoBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.QRCodeBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.UsuarioRetrievalDTO;
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

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ApiResponse<String> buildBoletimUrna(
        @AuthenticationPrincipal Usuario usuario,
        @Valid @RequestBody BoletimUrnaBuildDTO boletimUrnaBuildDTO
    ) {
        if (usuario == null) {
            throw new UsuarioNaoAutenticadoException("O usuário precisa estar autenticado para efetuar uma requisição de construção de boletim de urna.");
        }

        this.boletimUrnaUsuarioService.build(boletimUrnaBuildDTO, usuario);

        return new ApiResponse<>("O boletim de urna foi criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping(params = {"numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    @ResponseStatus(value = HttpStatus.OK)
    public BoletimUrnaRetrievalDTO findBoletimUrna(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(this.boletimUrnaService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoletimUrnaRetrievalDTO> findBoletinsUrna() {
        return this.boletimUrnaService
            .findAll()
            .stream()
            .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
            .toList();
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void delete(@Valid BoletimUrnaIdDTO id) {
        this.boletimUrnaService.deleteById(id);
    }

    @GetMapping(value = "/usuarios")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<UsuarioRetrievalDTO> findUsuarios(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaService
            .findUsuarios(id)
            .stream()
            .map(this.usuarioMapper::toUsuarioRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/qr-codes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<QRCodeBoletimUrnaRetrievalDTO> findQRCodes(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaService
            .findQRCodes(id)
            .stream()
            .map(this.qrCodeBoletimUrnaMapper::toQRCodeBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/apuracoes-votos-candidaturas")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO> findApuracoesVotosCandidaturas(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaService
            .findApuracoesVotosCandidaturas(id)
            .stream()
            .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/apuracoes-votos-cargos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ApuracaoVotosCargoBoletimUrnaRetrievalDTO> findApuracoesVotosCargos(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaService
            .findApuracoesVotosCargos(id)
            .stream()
            .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/apuracoes-votos-partidos")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ApuracaoVotosPartidoBoletimUrnaRetrievalDTO> findApuracoesVotosPartidos(@Valid BoletimUrnaIdDTO id) {
        return this.boletimUrnaService
            .findApuracoesVotosPartidos(id)
            .stream()
            .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
