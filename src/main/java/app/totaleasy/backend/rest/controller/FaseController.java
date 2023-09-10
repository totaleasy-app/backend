package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.FaseMapper;
import app.totaleasy.backend.rest.service.FaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/fases")
@RequiredArgsConstructor
public class FaseController {

    private final FaseService faseService;

    private final FaseMapper faseMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> findFase(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(status, this.faseMapper.toFaseRetrievalDTO(this.faseService.findByNome(nome))),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findFases() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.faseService
                    .findAll()
                    .stream()
                    .map(this.faseMapper::toFaseRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}/boletins-urna")
    public ResponseEntity<ApiResponse> findBoletinsUrna(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.faseService
                    .findBoletinsUrna(nome)
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
