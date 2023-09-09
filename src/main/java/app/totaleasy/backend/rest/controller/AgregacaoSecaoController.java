package app.totaleasy.backend.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.id.AgregacaoSecaoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.AgregacaoSecaoRetrievalDTO;
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
            "numeroTSESecaoPrincipal",
            "numeroTSEZonaSecaoPrincipal",
            "siglaUFSecaoPrincipal",
            "numeroTSESecaoAgregada",
            "numeroTSEZonaSecaoAgregada",
            "siglaUFSecaoAgregada",
            "codigoTSEProcessoEleitoral"
        }
    )
    @ResponseStatus(value = HttpStatus.OK)
    public AgregacaoSecaoRetrievalDTO findAgregacaoSecao(@Valid AgregacaoSecaoIdDTO id) {
        return this.agregacaoSecaoMapper.toAgregacaoSecaoRetrievalDTO(this.agregacaoSecaoService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AgregacaoSecaoRetrievalDTO> findAgregacoesSecao() {
        return this.agregacaoSecaoService
            .findAll()
            .stream()
            .map(this.agregacaoSecaoMapper::toAgregacaoSecaoRetrievalDTO)
            .toList();
    }
}
