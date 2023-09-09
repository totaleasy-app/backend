package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.UrnaEletronicaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.UrnaEletronicaMapper;
import app.totaleasy.backend.rest.service.UrnaEletronicaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/urnas-eletronicas")
@RequiredArgsConstructor
public class UrnaEletronicaController {

    private final UrnaEletronicaService urnaEletronicaService;

    private final UrnaEletronicaMapper urnaEletronicaMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(value = "/{numeroSerie}")
    @ResponseStatus(value = HttpStatus.OK)
    public UrnaEletronicaRetrievalDTO findUrnaEletronica(@PathVariable("numeroSerie") Integer numeroSerie) {
        return this.urnaEletronicaMapper
            .toUrnaEletronicaRetrievalDTO(this.urnaEletronicaService.findByNumeroSerie(numeroSerie));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UrnaEletronicaRetrievalDTO> findUrnasEletronicas() {
        return this.urnaEletronicaService
            .findAll()
            .stream()
            .map(this.urnaEletronicaMapper::toUrnaEletronicaRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{numeroSerie}/boletins-urna")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<BoletimUrnaRetrievalDTO> findBoletinsUrna(@PathVariable("numeroSerie") Integer numeroSerie) {
        return this.urnaEletronicaService
            .findBoletinsUrna(numeroSerie)
            .stream()
            .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
