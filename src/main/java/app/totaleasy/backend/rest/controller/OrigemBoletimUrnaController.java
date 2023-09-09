package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.OrigemBoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.OrigemBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.OrigemBoletimUrnaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/origens-boletim-urna")
@RequiredArgsConstructor
public class OrigemBoletimUrnaController {

    private final OrigemBoletimUrnaService origemBoletimUrnaService;

    private final OrigemBoletimUrnaMapper origemBoletimUrnaMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(value = "/{nomeAbreviado}")
    @ResponseStatus(value = HttpStatus.OK)
    public OrigemBoletimUrnaRetrievalDTO findOrigemBoletimUrna(@PathVariable("nomeAbreviado") String nomeAbreviado) {
        return this.origemBoletimUrnaMapper.toOrigemBoletimUrnaRetrievalDTO(
            this.origemBoletimUrnaService.findByNomeAbreviado(nomeAbreviado)
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrigemBoletimUrnaRetrievalDTO> findOrigensBoletimUrna() {
        return this.origemBoletimUrnaService
            .findAll()
            .stream()
            .map(this.origemBoletimUrnaMapper::toOrigemBoletimUrnaRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{nomeAbreviado}/boletins-urna")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<BoletimUrnaRetrievalDTO> findBoletinsUrna(@PathVariable("nomeAbreviado") String nomeAbreviado) {
        return this.origemBoletimUrnaService
            .findBoletinsUrna(nomeAbreviado)
            .stream()
            .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
