package app.totaleasy.backend.core.model;

import app.totaleasy.backend.core.util.DataTypeConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarcaInicioDados {

    private int indiceQRCode;

    private int quantidadeTotalQRCodes;

    public MarcaInicioDados(String string) {
        if (string == null) {
            throw new NullPointerException("A marca de início dos dados do boletim de urna deve ser informada.");
        }

        String[] strings = string.split(":");

        this.indiceQRCode = DataTypeConverter.toInteger(strings[0]);
        this.quantidadeTotalQRCodes = DataTypeConverter.toInteger(strings[1]);
    }
}
