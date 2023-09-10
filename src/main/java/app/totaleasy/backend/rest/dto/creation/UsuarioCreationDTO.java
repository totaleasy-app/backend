package app.totaleasy.backend.rest.dto.creation;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@ToString(doNotUseGetters = true)
public class UsuarioCreationDTO implements ValidatableDTO {

    @NotBlank(message = "O campo 'username' deve ser informado ao criar um usuário.")
    @Size(min = 1, max = 31, message = "O campo 'username' deve possuir no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotBlank(message = "O campo 'senha' deve ser informado ao criar um usuário.")
    @Size(min = 8, max = 72, message = "O campo 'senha' deve possuir no mínimo 8 caracteres e no máximo 72 caracteres.")
    private String senha;

    @Size(min = 1, max = 127, message = "O campo 'nome' deve possuir no mínimo 1 caractere e no máximo 127 caracteres.")
    private String nome;

    @Size(min = 1, max = 127, message = "O campo 'sobrenome' deve possuir no mínimo 1 caractere e no máximo 127 caracteres.")
    private String sobrenome;

    @NotEmpty(message = "O campo 'papeis' deve ser informado ao criar um usuário.")
    private Set<String> papeis;

    @Override
    public void validate() {
        if (StringUtils.isBlank(this.username)) {
            throw new CampoFaltanteException("O campo 'username' deve ser informado ao criar um usuário.");
        }

        if (this.username.isEmpty() || this.username.length() > 31) {
            throw new ValorInvalidoException("O campo 'username' deve possuir no mínimo 1 caractere e no máximo 31 caracteres.");
        }

        if (StringUtils.isBlank(this.senha)) {
            throw new CampoFaltanteException("O campo 'senha' deve ser informado ao criar um usuário.");
        }

        if (this.senha.length() < 8 || this.senha.length() > 72) {
            throw new ValorInvalidoException("O campo 'senha' deve possuir no mínimo 8 caracteres e no máximo 72 caracteres.");
        }

        if (this.nome != null && (this.nome.isEmpty() || this.nome.length() > 127)) {
            throw new ValorInvalidoException("O campo 'nome' deve possuir no mínimo 1 caractere e no máximo 127 caracteres.");
        }

        if (this.sobrenome != null && (this.sobrenome.isEmpty() || this.sobrenome.length() > 127)) {
            throw new ValorInvalidoException("O campo 'sobrenome' deve possuir no mínimo 1 caractere e no máximo 127 caracteres.");
        }

        if (this.papeis == null || this.papeis.isEmpty()) {
            throw new CampoFaltanteException("O campo 'papeis' deve ser informado ao criar um usuário.");
        }
    }
}
