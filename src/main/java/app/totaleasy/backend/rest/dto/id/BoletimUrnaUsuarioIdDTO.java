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
public class BoletimUrnaUsuarioIdDTO implements ValidatableDTO {

    @NotBlank(message = "O username deve ser informado para identificar uma relação entre boletim de urna e usuário.")
    @Size(min = 1, max = 31, message = "O username deve possuir no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotNull(message = "O número da seção deve ser informado para identificar uma relação entre boletim de urna e usuário.")
    private Integer numeroTSESecao;

    @NotNull(message = "O número da zona deve ser informado para identificar uma relação entre boletim de urna e usuário.")
    private Integer numeroTSEZona;

    @NotBlank(message = "A sigla da UF deve ser informada para identificar uma relação entre boletim de urna e usuário.")
    @Size(min = 2, max = 2, message = "A sigla da UF deve possuir 2 caracteres.")
    private String siglaUF;

    @NotNull(message = "O código do pleito deve ser informado para identificar uma relação entre boletim de urna e usuário.")
    private Integer codigoTSEPleito;

    @Override
    public void validate() {
        if (StringUtils.isBlank(this.username)) {
            throw new CampoFaltanteException("O username deve ser informado para identificar uma relação entre boletim de urna e usuário.");
        }

        if (this.username.isEmpty() || this.username.length() > 31) {
            throw new ValorInvalidoException("O username deve possuir no mínimo 1 caractere e no máximo 31 caracteres.");
        }

        if (this.numeroTSEZona == null) {
            throw new CampoFaltanteException("O número da zona deve ser informado para identificar uma relação entre boletim de urna e usuário.");
        }

        if (StringUtils.isBlank(this.siglaUF)) {
            throw new CampoFaltanteException("A sigla da UF deve ser informada para identificar uma relação entre boletim de urna e usuário.");
        }

        if (this.siglaUF.length() != 2) {
            throw new ValorInvalidoException("A sigla da UF deve possuir 2 caracteres.");
        }

        if (this.codigoTSEPleito == null) {
            throw new CampoFaltanteException("O código do pleito deve ser informado para identificar uma relação entre boletim de urna e usuário.");
        }
    }
}
