package app.totaleasy.backend.rest.dto.build;

import java.util.List;

import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletimUrnaBuildDTO {

    @NotNull(message = "O(s) payload(s) do(s) QR code(s) do boletim de urna deve(m) ser informado(s) para construir um boletim de urna.")
    @NotEmpty(message = "O(s) payload(s) do(s) QR code(s) do boletim de urna deve(m) ser informado(s) para construir um boletim de urna.")
    private List<String> payloads;

    public void validate() {
        if (this.payloads == null || this.payloads.isEmpty()) {
            throw new CampoFaltanteException(
                "O(s) payload(s) do(s) QR code(s) do boletim de urna deve(m) ser informado(s) para construir um boletim de urna."
            );
        }
    }
}
