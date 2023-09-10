package app.totaleasy.backend.rest.dto.request;

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
public class AuthenticationRequestDTO implements ValidatableDTO {

    @NotBlank(message = "O username deve ser informado para autenticar um usuário.")
    @Size(min = 1, max = 31, message = "O username deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotBlank(message = "A senha deve ser informada para autenticar um usuário.")
    @Size(min = 8, max = 72, message = "A senha deve ter no mínimo 8 caracteres e no máximo 72 caracteres.")
    private String senha;

    @Override
    public void validate() {
        if (StringUtils.isBlank(this.username)) {
            throw new CampoFaltanteException("O username deve ser informado para autenticar um usuário.");
        }

        if (this.username.isEmpty() || this.username.length() > 31) {
            throw new ValorInvalidoException("O username deve possuir no mínimo 1 caractere e no máximo 31 caracteres.");
        }

        if (StringUtils.isBlank(this.senha)) {
            throw new CampoFaltanteException("A senha deve ser informada para criar um usuário.");
        }

        if (this.senha.length() < 8 || this.senha.length() > 72) {
            throw new ValorInvalidoException("A senha deve possuir no mínimo 8 caracteres e no máximo 72 caracteres.");
        }
    }
}
