package app.totaleasy.backend.rest.dto.update;

import org.apache.commons.lang3.StringUtils;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO implements ValidatableDTO {

    @Size(min = 8, max = 72, message = "A senha do usuário deve ter no mínimo 8 caracteres e no máximo 72 caracteres.")
    private String senha;

    @Size(min = 1, max = 127, message = "O nome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String nome;

    @Size(min = 1, max = 127, message = "O sobrenome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String sobrenome;

    @Override
    public void validate() {
        if (!StringUtils.isBlank(this.senha) && (this.senha.length() < 8 || this.senha.length() > 72)) {
            throw new ValorInvalidoException("A senha deve possuir no mínimo 8 caracteres e no máximo 72 caracteres.");
        }

        if (this.nome != null && (this.nome.isEmpty() || this.nome.length() > 127)) {
            throw new ValorInvalidoException("O nome deve possuir no mínimo 1 caractere e no máximo 127 caracteres.");
        }

        if (this.sobrenome != null && (this.sobrenome.isEmpty() || this.sobrenome.length() > 127)) {
            throw new ValorInvalidoException("O sobrenome deve possuir no mínimo 1 caractere e no máximo 127 caracteres.");
        }

        if (StringUtils.isBlank(this.senha) && StringUtils.isBlank(this.nome) && StringUtils.isBlank(this.sobrenome)) {
            throw new CampoFaltanteException("A senha, nome, sobrenome ou todos os campos mencionados anteriormente devem ser informados para atualizar um usuário.");
        }
    }
}
