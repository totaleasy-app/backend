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
public class CandidaturaIdDTO implements ValidatableDTO {

    @NotNull(message = "O número do candidato deve ser informado para identificar uma candidatura.")
    private Integer numeroTSECandidato;

    @NotNull(message = "O código do cargo deve ser informado para identificar uma candidatura.")
    private Integer codigoTSECargo;

    @NotNull(message = "O código da eleição deve ser informado para identificar uma candidatura.")
    private Integer codigoTSEEleicao;

    @Override
    public void validate() {
        if (this.numeroTSECandidato == null) {
            throw new CampoFaltanteException("O número do candidato deve ser informado para identificar uma candidatura.");
        }

        if (this.codigoTSECargo == null) {
            throw new CampoFaltanteException("O código do cargo deve ser informado para identificar uma candidatura.");
        }

        if (this.codigoTSEEleicao == null) {
            throw new CampoFaltanteException("O código da eleição deve ser informado para identificar uma candidatura.");
        }
    }
}
