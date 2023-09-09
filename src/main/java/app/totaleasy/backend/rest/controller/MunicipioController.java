package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.MunicipioRetrievalDTO;
import app.totaleasy.backend.rest.mapper.LocalVotacaoMapper;
import app.totaleasy.backend.rest.mapper.MunicipioMapper;
import app.totaleasy.backend.rest.service.MunicipioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    private final MunicipioMapper municipioMapper;

    private final LocalVotacaoMapper localVotacaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public MunicipioRetrievalDTO findMunicipio(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.municipioMapper.toMunicipioRetrievalDTO(this.municipioService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<MunicipioRetrievalDTO> findMunicipios() {
        return this.municipioService
            .findAll()
            .stream()
            .map(this.municipioMapper::toMunicipioRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/locais-votacao")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<LocalVotacaoRetrievalDTO> findLocaisVotacao(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.municipioService
            .findLocaisVotacao(codigoTSE)
            .stream()
            .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
