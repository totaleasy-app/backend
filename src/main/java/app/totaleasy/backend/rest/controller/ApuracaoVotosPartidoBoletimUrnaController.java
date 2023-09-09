package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.ApuracaoVotosPartidoBoletimUrnaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/apuracoes-votos-partidos-boletim-urna")
@RequiredArgsConstructor
public class ApuracaoVotosPartidoBoletimUrnaController {

    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @GetMapping(params = {"numeroTSEPartido", "codigoTSECargo", "codigoTSEEleicao", "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosPartidoBoletimUrnaRetrievalDTO findApuracaoVotosPartidoBoletimUrna(
        @Valid ApuracaoVotosPartidoBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosPartidoBoletimUrnaMapper
            .toApuracaoVotosPartidoBoletimUrnaRetrievalDTO(
                this.apuracaoVotosPartidoBoletimUrnaService.findById(id)
            );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosPartidoBoletimUrnaRetrievalDTO> findApuracoesVotosPartidosBoletimUrna() {
        return this.apuracaoVotosPartidoBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
            .toList();
    }
}
