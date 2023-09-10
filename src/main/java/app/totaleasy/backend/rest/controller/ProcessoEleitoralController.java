package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.AgregacaoSecaoMapper;
import app.totaleasy.backend.rest.mapper.PleitoMapper;
import app.totaleasy.backend.rest.mapper.ProcessoEleitoralMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.ProcessoEleitoralService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/processos-eleitorais")
@RequiredArgsConstructor
public class ProcessoEleitoralController {

    private final ProcessoEleitoralService processoEleitoralService;

    private final ProcessoEleitoralMapper processoEleitoralMapper;

    private final PleitoMapper pleitoMapper;

    private final SecaoMapper secaoMapper;

    private final AgregacaoSecaoMapper agregacaoSecaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    public ResponseEntity<ApiResponse> findProcessoEleitoral(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.processoEleitoralMapper
                    .toProcessoEleitoralRetrievalDTO(this.processoEleitoralService.findByCodigoTSE(codigoTSE))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findProcessosEleitorais() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.processoEleitoralService
                    .findAll()
                    .stream()
                    .map(this.processoEleitoralMapper::toProcessoEleitoralRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/secoes")
    public ResponseEntity<ApiResponse> findSecoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.processoEleitoralService
                    .findSecoes(codigoTSE)
                    .stream()
                    .map(this.secaoMapper::toSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/agregacoes-secao")
    public ResponseEntity<ApiResponse> findAgregacoesSecao(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.processoEleitoralService
                    .findSecoesAgregadas(codigoTSE)
                    .stream()
                    .map(this.agregacaoSecaoMapper::toAgregacaoSecaoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }

    @GetMapping(value = "/{codigoTSE}/pleitos")
    public ResponseEntity<ApiResponse> findPleitos(@PathVariable("codigoTSE") Integer codigoTSE) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.processoEleitoralService
                    .findPleitos(codigoTSE)
                    .stream()
                    .map(this.pleitoMapper::toPleitoRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
