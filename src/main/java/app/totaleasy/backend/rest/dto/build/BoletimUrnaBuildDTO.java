package app.totaleasy.backend.rest.dto.build;

import java.util.List;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;

import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletimUrnaBuildDTO implements ValidatableDTO {

    @NotEmpty(message = "O(s) payload(s) do(s) QR code(s) do boletim de urna deve(m) ser informado(s) para construir um boletim de urna.")
    private List<String> payloads;

    @Override
    public void validate() {
        if (this.payloads == null || this.payloads.isEmpty()) {
            throw new CampoFaltanteException(
                "O(s) payload(s) do(s) QR code(s) do boletim de urna deve(m) ser informado(s) para construir um boletim de urna."
            );
        }
    }
}
