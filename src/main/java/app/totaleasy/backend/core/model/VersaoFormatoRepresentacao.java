package app.totaleasy.backend.core.model;

import app.totaleasy.backend.core.util.DataTypeConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VersaoFormatoRepresentacao {

    private Integer numeroCiclosEleitoraisDesdeImplementacao;

    private Integer numeroRevisoesFormatoCiclo;

    public VersaoFormatoRepresentacao(String string) {
        if (string == null) {
            throw new NullPointerException(
                "A versão do formato de representação do boletim de urna deve ser informada."
            );
        }

        String[] numeros = string.split("\\.");

        this.numeroCiclosEleitoraisDesdeImplementacao = DataTypeConverter.toInteger(numeros[0]);
        this.numeroRevisoesFormatoCiclo = DataTypeConverter.toInteger(numeros[1]);
    }
}
