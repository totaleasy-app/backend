package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.ZonaIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.SecaoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.ZonaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.mapper.ZonaMapper;
import app.totaleasy.backend.rest.service.ZonaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/zonas")
@RequiredArgsConstructor
public class ZonaController {

    private final ZonaService zonaService;

    private final ZonaMapper zonaMapper;

    private final SecaoMapper secaoMapper;

    private final LocalVotacaoMapper localVotacaoMapper;

    @GetMapping(params = {"numeroTSEZona", "siglaUF"})
    @ResponseStatus(value = HttpStatus.OK)
    public ZonaRetrievalDTO findZona(@Valid ZonaIdDTO id) {
        return this.zonaMapper.toZonaRetrievalDTO(this.zonaService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ZonaRetrievalDTO> findZonas() {
        return this.zonaService
            .findAll()
            .stream()
            .map(this.zonaMapper::toZonaRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/secoes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<SecaoRetrievalDTO> findSecoes(@Valid ZonaIdDTO id) {
        return this.zonaService
            .findSecoes(id)
            .stream()
            .map(this.secaoMapper::toSecaoRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/locais-votacao")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<LocalVotacaoRetrievalDTO> findLocaisVotacao(@Valid ZonaIdDTO id) {
        return this.zonaService
            .findLocaisVotacao(id)
            .stream()
            .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
