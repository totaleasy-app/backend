package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.CargoEleicaoIdDTO;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosCargoBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.CandidaturaMapper;
import app.totaleasy.backend.rest.mapper.CargoEleicaoMapper;
import app.totaleasy.backend.rest.service.CargoEleicaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/cargos-eleicoes")
@RequiredArgsConstructor
public class CargoEleicaoController {

    private final CargoEleicaoService cargoEleicaoService;

    private final CargoEleicaoMapper cargoEleicaoMapper;

    private final CandidaturaMapper candidaturaMapper;

    private final ApuracaoVotosCargoBoletimUrnaMapper apuracaoVotosCargoBoletimUrnaMapper;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @GetMapping(params = {"codigoTSECargo", "codigoTSEEleicao"})
    public ResponseEntity<ApiResponse> findCargoEleicao(@Valid CargoEleicaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(this.cargoEleicaoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findCargosEleicoes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoEleicaoService
                    .findAll()
                    .stream()
                    .map(this.cargoEleicaoMapper::toCargoEleicaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/candidaturas")
    public ResponseEntity<ApiResponse> findCandidaturas(@Valid CargoEleicaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoEleicaoService
                    .findCandidaturas(id)
                    .stream()
                    .map(this.candidaturaMapper::toCandidaturaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/apuracoes-votos-cargos-boletim-urna")
    public ResponseEntity<ApiResponse> findApuracoesVotosCargosBoletimUrna(@Valid CargoEleicaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoEleicaoService
                    .findApuracoesVotosCargosBoletimUrna(id)
                    .stream()
                    .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/apuracoes-votos-partidos-boletim-urna")
    public ResponseEntity<ApiResponse> findApuracoesVotosPartidosBoletimUrna(@Valid CargoEleicaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.cargoEleicaoService
                    .findApuracoesVotosPartidosBoletimUrna(id)
                    .stream()
                    .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
