package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.LocalVotacaoIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.SecaoRetrievalDTO;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.SecaoMapper;
import app.totaleasy.backend.rest.service.LocalVotacaoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/locais-votacao")
@RequiredArgsConstructor
public class LocalVotacaoController {

    private final LocalVotacaoService localVotacaoService;

    private final LocalVotacaoMapper localVotacaoMapper;

    private final SecaoMapper secaoMapper;

    @GetMapping(params = {"numeroTSELocalVotacao", "numeroTSEZona", "siglaUF"})
    @ResponseStatus(value = HttpStatus.OK)
    public LocalVotacaoRetrievalDTO findLocalVotacao(@Valid LocalVotacaoIdDTO id) {
        return this.localVotacaoMapper.toLocalVotacaoRetrievalDTO(this.localVotacaoService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<LocalVotacaoRetrievalDTO> findLocaisVotacao() {
        return this.localVotacaoService
            .findAll()
            .stream()
            .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/secoes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<SecaoRetrievalDTO> findSecoes(@Valid LocalVotacaoIdDTO id) {
        return this.localVotacaoService
            .findSecoes(id)
            .stream()
            .map(this.secaoMapper::toSecaoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
