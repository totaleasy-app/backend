package app.totaleasy.backend.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.totaleasy.backend.rest.dto.api.ApiResponse;
import app.totaleasy.backend.rest.dto.id.QRCodeBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.mapper.QRCodeBoletimUrnaMapper;
import app.totaleasy.backend.rest.service.QRCodeBoletimUrnaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/qr-codes-boletim-urna")
@RequiredArgsConstructor
public class QRCodeBoletimUrnaController {

    private final QRCodeBoletimUrnaService qrCodeBoletimUrnaService;

    private final QRCodeBoletimUrnaMapper qrCodeBoletimUrnaMapper;

    @GetMapping(params = {"indice", "numeroTSESecao", "numeroTSEZona", "siglaUF", "codigoTSEPleito"})
    public ResponseEntity<ApiResponse> findQRCodeBoletimUrna(@Valid QRCodeBoletimUrnaIdDTO id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.qrCodeBoletimUrnaMapper.toQRCodeBoletimUrnaRetrievalDTO(this.qrCodeBoletimUrnaService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findQRCodesBoletimUrna() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.qrCodeBoletimUrnaService
                    .findAll()
                    .stream()
                    .map(this.qrCodeBoletimUrnaMapper::toQRCodeBoletimUrnaRetrievalDTO)
                    .toList()
            ),
            status
        );
    }
}
