package app.totaleasy.backend.rest.dto.id;

import app.totaleasy.backend.rest.dto.ValidatableDTO;
import app.totaleasy.backend.rest.exception.CampoFaltanteException;

import jakarta.validation.constraints.NotNull;

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
public class CargoEleicaoIdDTO implements ValidatableDTO {

    @NotNull(message = "O código do cargo deve ser informado para identificar uma relação entre cargo e eleição.")
    private Integer codigoTSECargo;

    @NotNull(message = "O código da eleição deve ser informado para identificar uma relação entre cargo e eleição.")
    private Integer codigoTSEEleicao;

    @Override
    public void validate() {
        if (this.codigoTSECargo == null) {
            throw new CampoFaltanteException("O código do cargo deve ser informado para identificar uma relação entre cargo e eleição.");
        }

        if (this.codigoTSEEleicao == null) {
            throw new CampoFaltanteException("O código da eleição deve ser informado para identificar uma relação entre cargo e eleição.");
        }
    }
}
