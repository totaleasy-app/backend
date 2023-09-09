package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.MunicipioRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.UFRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.ZonaRetrievalDTO;
import app.totaleasy.backend.rest.mapper.MunicipioMapper;
import app.totaleasy.backend.rest.mapper.UFMapper;
import app.totaleasy.backend.rest.mapper.ZonaMapper;
import app.totaleasy.backend.rest.service.UFService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/ufs")
@RequiredArgsConstructor
public class UFController {

    private final UFService ufService;

    private final UFMapper ufMapper;

    private final ZonaMapper zonaMapper;

    private final MunicipioMapper municipioMapper;

    @GetMapping(value = "/{sigla}")
    @ResponseStatus(value = HttpStatus.OK)
    public UFRetrievalDTO findUF(@PathVariable("sigla") String sigla) {
        return this.ufMapper.toUFRetrievalDTO(this.ufService.findBySigla(sigla));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UFRetrievalDTO> findUFs() {
        return this.ufService
            .findAll()
            .stream()
            .map(this.ufMapper::toUFRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{sigla}/zonas")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<ZonaRetrievalDTO> findZonas(@PathVariable("sigla") String sigla) {
        return this.ufService
            .findZonas(sigla)
            .stream()
            .map(this.zonaMapper::toZonaRetrievalDTO)
            .collect(Collectors.toSet());
    }

    @GetMapping(value = "/{sigla}/municipios")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<MunicipioRetrievalDTO> findMunicipios(@PathVariable("sigla") String sigla) {
        return this.ufService
            .findMunicipios(sigla)
            .stream()
            .map(this.municipioMapper::toMunicipioRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
