package app.totaleasy.backend.rest.dto.id;

import app.totaleasy.backend.rest.exception.CampoFaltanteException;
import app.totaleasy.backend.rest.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PapelPermissaoIdDTO {

    @NotBlank(message = "O nome do papel do usuário deve ser informado para identificar uma relação entre papel e permissão.")
    @Size(min = 1, max = 15, message = "O nome do papel do usuário deve possuir no mínimo 1 caractere e no máximo 15 caracteres.")
    private String nomePapel;

    @NotBlank(message = "O nome da permissão deve ser informado para identificar uma relação entre papel e permissão.")
    @Size(min = 1, max = 31, message = "O nome da permissão deve possuir no mínimo 1 caractere e no máximo 31 caracteres.")
    private String nomePermissao;

    public void validate() {
        if (StringUtils.isBlank(this.nomePapel)) {
            throw new CampoFaltanteException("O nome do papel do usuário deve ser informado para identificar uma relação entre papel e permissão.");
        }

        if (this.nomePapel.length() < 1 || this.nomePapel.length() > 15) {
            throw new ValorInvalidoException("O nome do papel do usuário deve possuir no mínimo 1 caractere e no máximo 15 caracteres.");
        }

        if (StringUtils.isBlank(this.nomePermissao)) {
            throw new CampoFaltanteException("O nome da permissão deve ser informado para identificar uma relação entre papel e permissão.");
        }

        if (this.nomePermissao.length() < 1 || this.nomePermissao.length() > 31) {
            throw new ValorInvalidoException("O nome da permissão deve possuir no mínimo 1 caractere e no máximo 31 caracteres.");
        }
    }
}
