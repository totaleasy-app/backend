package app.totaleasy.backend.rest.dto.id;

import org.apache.commons.lang3.StringUtils;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
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
public class PapelUsuarioIdDTO implements ValidatableDTO {

    @NotBlank(message = "O username deve ser informado para identificar uma relação entre usuário e papel.")
    @Size(min = 1, max = 31, message = "O username deve possuir no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotBlank(message = "O nome do papel do usuário deve ser informado para identificar uma relação entre usuário e papel.")
    @Size(min = 1, max = 15, message = "O nome do papel do usuário deve possuir no mínimo 1 caractere e no máximo 15 caracteres.")
    private String nomePapel;

    @Override
    public void validate() {
        if (StringUtils.isBlank(this.username)) {
            throw new CampoFaltanteException("O username deve ser informado para identificar uma relação entre usuário e papel.");
        }

        if (this.username.isEmpty() || this.username.length() > 31) {
            throw new ValorInvalidoException("O username deve possuir no mínimo 1 caractere e no máximo 31 caracteres.");
        }

        if (StringUtils.isBlank(this.nomePapel)) {
            throw new CampoFaltanteException("O nome do papel do usuário deve ser informado para identificar uma relação entre usuário e papel.");
        }

        if (this.nomePapel.isEmpty() || this.nomePapel.length() > 15) {
            throw new ValorInvalidoException("O nome do papel do usuário deve possuir no mínimo 1 caractere e no máximo 15 caracteres.");
        }
    }
}
