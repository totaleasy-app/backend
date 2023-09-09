package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.ApuracaoVotosCargoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCargoBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCargoBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.ApuracaoVotosCargoBoletimUrnaService;

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
@RequestMapping(value = "/api/apuracoes-votos-cargos-boletim-urna")
@RequiredArgsConstructor
public class ApuracaoVotosCargoBoletimUrnaController {

    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;

    private final ApuracaoVotosCargoBoletimUrnaMapper apuracaoVotosCargoBoletimUrnaMapper;

    @GetMapping(params = {"codigoTSECargo", "codigoTSEEleicao", "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosCargoBoletimUrnaRetrievalDTO findApuracaoVotosCargoBoletimUrna(
        @Valid ApuracaoVotosCargoBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosCargoBoletimUrnaMapper
            .toApuracaoVotosCargoBoletimUrnaRetrievalDTO(
                this.apuracaoVotosCargoBoletimUrnaService.findById(id)
            );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCargoBoletimUrnaRetrievalDTO> findApuracoesVotosCargosBoletimUrna() {
        return this.apuracaoVotosCargoBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
            .toList();
    }
}
