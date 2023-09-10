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
@ToString(doNotUseGetters = true)
public class AgregacaoSecaoIdDTO implements ValidatableDTO {

    @NotNull(message = "O número da seção principal deve ser informado para identificar uma agregação de seção.")
    private Integer numeroTSESecaoPrincipal;

    @NotNull(message = "O número da zona da seção principal deve ser informado para identificar uma agregação de seção.")
    private Integer numeroTSEZonaSecaoPrincipal;

    @NotBlank(message = "A sigla da UF da seção principal deve ser informada para identificar uma agregação de seção.")
    @Size(min = 2, max = 2, message = "A sigla da UF da seção principal deve possuir 2 caracteres.")
    private String siglaUFSecaoPrincipal;

    @NotNull(message = "O número da seção agregada deve ser informado para identificar uma agregação de seção.")
    private Integer numeroTSESecaoAgregada;

    @NotNull(message = "O número da zona da seção agregada deve ser informado para identificar uma agregação de seção.")
    private Integer numeroTSEZonaSecaoAgregada;

    @NotBlank(message = "A sigla da UF da seção agregada deve ser informada para identificar uma agregação de seção.")
    @Size(min = 2, max = 2, message = "A sigla da UF da seção agregada deve possuir 2 caracteres.")
    private String siglaUFSecaoAgregada;

    @NotNull(message = "O código do processo eleitoral deve ser informado para identificar uma agregação de seção.")
    private Integer codigoTSEProcessoEleitoral;

    @Override
    public void validate() {
        if (this.numeroTSESecaoPrincipal == null) {
            throw new CampoFaltanteException("O número da seção principal deve ser informado para identificar uma agregação de seção.");
        }

        if (this.numeroTSEZonaSecaoPrincipal == null) {
            throw new CampoFaltanteException("O número da zona da seção principal deve ser informado para identificar uma agregação de seção.");
        }

        if (StringUtils.isBlank(this.siglaUFSecaoPrincipal)) {
            throw new CampoFaltanteException("A sigla da UF da seção principal deve ser informada para identificar uma agregação de seção.");
        }

        if (this.siglaUFSecaoPrincipal.length() != 2) {
            throw new ValorInvalidoException("A sigla da UF da seção principal deve possuir 2 caracteres.");
        }

        if (this.numeroTSESecaoAgregada == null) {
            throw new CampoFaltanteException("O número da seção agregada deve ser informado para identificar uma agregação de seção.");
        }

        if (this.numeroTSEZonaSecaoAgregada == null) {
            throw new CampoFaltanteException("O número da zona da seção agregada deve ser informado para identificar uma agregação de seção.");
        }

        if (StringUtils.isBlank(this.siglaUFSecaoAgregada)) {
            throw new CampoFaltanteException("A sigla da UF da seção agregada deve ser informada para identificar uma agregação de seção.");
        }

        if (this.siglaUFSecaoAgregada.length() != 2) {
            throw new ValorInvalidoException("A sigla da UF da seção agregada deve possuir 2 caracteres.");
        }

        if (this.codigoTSEProcessoEleitoral == null) {
            throw new CampoFaltanteException("O código do processo eleitoral deve ser informado para identificar uma agregação de seção.");
        }
    }
}
