package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.retrieval.CargoRetrievalDTO;
import app.totaleasy.backend.rest.dto.retrieval.EleicaoRetrievalDTO;
import app.totaleasy.backend.rest.mapper.CargoMapper;
import app.totaleasy.backend.rest.mapper.EleicaoMapper;
import app.totaleasy.backend.rest.service.CargoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    private final CargoMapper cargoMapper;

    private final EleicaoMapper eleicaoMapper;

    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public CargoRetrievalDTO findCargo(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.cargoMapper.toCargoRetrievalDTO(this.cargoService.findByCodigoTSE(codigoTSE));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CargoRetrievalDTO> findCargos() {
        return this.cargoService
            .findAll()
            .stream()
            .map(this.cargoMapper::toCargoRetrievalDTO)
            .toList();
    }

    @GetMapping(value = "/{codigoTSE}/eleicoes")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<EleicaoRetrievalDTO> findEleicoes(@PathVariable("codigoTSE") Integer codigoTSE) {
        return this.cargoService
            .findEleicoes(codigoTSE)
            .stream()
            .map(this.eleicaoMapper::toEleicaoRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
