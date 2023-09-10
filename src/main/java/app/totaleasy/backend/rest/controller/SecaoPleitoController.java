package app.totaleasy.backend.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.SecaoPleitoIdDTO;
import app.totaleasy.backend.rest.mapper.BoletimUrnaMapper;
import app.totaleasy.backend.rest.mapper.SecaoPleitoMapper;
import app.totaleasy.backend.rest.service.SecaoPleitoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/secoes-pleitos")
@RequiredArgsConstructor
public class SecaoPleitoController {

    private final SecaoPleitoService secaoPleitoService;

    private final SecaoPleitoMapper secaoPleitoMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @GetMapping(params = {"numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    public ResponseEntity<ApiResponse> findSecaoPleito(@Valid SecaoPleitoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoPleitoMapper.toSecaoPleitoRetrievalDTO(this.secaoPleitoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findSecoesPleitos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoPleitoService
                    .findAll()
                    .stream()
                    .map(this.secaoPleitoMapper::toSecaoPleitoRetrievalDTO)
                    .toList()
            ),
            status
        );
    }

    @GetMapping(value = "/boletins-urna")
    public ResponseEntity<ApiResponse> findBoletinsUrna(@Valid SecaoPleitoIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.secaoPleitoService
                    .findBoletinsUrna(id)
                    .stream()
                    .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
                    .collect(Collectors.toSet())
            ),
            status
        );
    }
}
