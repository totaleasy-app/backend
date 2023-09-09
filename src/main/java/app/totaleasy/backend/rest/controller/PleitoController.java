package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.EleicaoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.PleitoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.SecaoRetrievalDTO;
import app.totaleasy.backend.rest.mapper.EleicaoMapper;
import app.totaleasy.backend.rest.mapper.PleitoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.PleitoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/pleitos")
@RequiredArgsConstructor
public class PleitoController {

    private final PleitoService pleitoService;

    private final PleitoMapper pleitoMapper;

    private final EleicaoMapper eleicaoMapper;

    private final SecaoMapper secaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public PleitoRetrievalDTO findPleito(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.pleitoMapper.toPleitoRetrievalDTO(this.pleitoService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PleitoRetrievalDTO> findPleitos() {
        return this.pleitoService
            .findAll()
            .stream()
            .map(this.pleitoMapper::toPleitoRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/secoes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<SecaoRetrievalDTO> findSecoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.pleitoService
            .findSecoes(codigoTSE)
            .stream()
            .map(this.secaoMapper::toSecaoRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/{codigoTSE}/eleicoes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<EleicaoRetrievalDTO> findEleicoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.pleitoService
            .findEleicoes(codigoTSE)
            .stream()
            .map(this.eleicaoMapper::toEleicaoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
