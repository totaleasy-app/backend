package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.AgregacaoSecaoIdDTO;
import app.totaleasy.backend.rest.mapper.AgregacaoSecaoMapper;
import app.totaleasy.backend.rest.service.AgregacaoSecaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/agregacoes-secao")
@RequiredArgsConstructor
public class AgregacaoSecaoController {

    private final AgregacaoSecaoService agregacaoSecaoService;

    private final AgregacaoSecaoMapper agregacaoSecaoMapper;

    @GetMapping(
        params = {
            "numeroTSESecaoPrincipal", "numeroTSEZonaSecaoPrincipal", "siglaUFSecaoPrincipal",
            "numeroTSESecaoAgregada", "numeroTSEZonaSecaoAgregada", "siglaUFSecaoAgregada",
            "codigoTSEProcessoEleitoral"
        }
    )
    public ResponseEntity<ApiResponse> findAgregacaoSecao(@Valid AgregacaoSecaoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.agregacaoSecaoMapper.toAgregacaoSecaoRetrievalDTO(this.agregacaoSecaoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAgregacoesSecao() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.agregacaoSecaoService
                    .findAll()
                    .stream()
                    .map(this.agregacaoSecaoMapper::toAgregacaoSecaoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
