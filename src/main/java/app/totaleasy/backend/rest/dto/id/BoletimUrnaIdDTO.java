package app.totaleasy.backend.rest.dto.id;

import org.apache.commons.lang3.StringUtils;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoletimUrnaIdDTO implements ValidatableDTO {

    @NotNull(message = "O número da seção deve ser informado para identificar um boletim de urna.")
    private Integer numeroTSESecao;

    @NotNull(message = "O número da zona deve ser informado para identificar um boletim de urna.")
    private Integer numeroTSEZona;

    @NotBlank(message = "A sigla da UF deve ser informada para identificar um boletim de urna.")
    @Size(min = 2, max = 2, message = "A sigla da UF deve possuir 2 caracteres.")
    private String siglaUF;

    @NotNull(message = "O código do pleito deve ser informado para identificar um boletim de urna.")
    private Integer codigoTSEPleito;

    @Override
    public void validate() {
        if (this.numeroTSESecao == null) {
            throw new CampoFaltanteException("O número da seção deve ser informado para identificar um boletim de urna.");
        }

        if (this.numeroTSEZona == null) {
            throw new CampoFaltanteException("O número da zona deve ser informado para identificar um boletim de urna.");
        }

        if (StringUtils.isBlank(this.siglaUF)) {
            throw new CampoFaltanteException("A sigla da UF deve ser informada para identificar um boletim de urna.");
        }

        if (this.siglaUF.length() != 2) {
            throw new ValorInvalidoException("A sigla da UF deve possuir 2 caracteres.");
        }

        if (this.codigoTSEPleito == null) {
            throw new CampoFaltanteException("O código do pleito deve ser informado para identificar um boletim de urna.");
        }
    }
}
