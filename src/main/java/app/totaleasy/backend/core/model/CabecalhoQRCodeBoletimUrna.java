package app.totaleasy.backend.core.model;

import app.totaleasy.backend.core.util.DataTypeConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class CabecalhoQRCodeBoletimUrna {

    private MarcaInicioDados marcaInicioDados;

    private VersaoFormatoRepresentacao versaoFormatoRepresentacao;

    private int versaoChaveAssinatura;

    public void setMarcaInicioDados(String marcaInicioDados) {
        if (marcaInicioDados == null) {
            throw new NullPointerException("A marca de início dos dados do boletim de urna deve ser informada.");
        }

        this.marcaInicioDados = new MarcaInicioDados(marcaInicioDados);
    }

    public void setVersaoFormatoRepresentacao(String versaoFormatoRepresentacao) {
        if (versaoFormatoRepresentacao == null) {
            throw new NullPointerException(
                "A versão do formato de representação do boletim de urna deve ser informada."
            );
        }

        this.versaoFormatoRepresentacao = new VersaoFormatoRepresentacao(versaoFormatoRepresentacao);
    }

    public void setVersaoChaveAssinatura(String versaoChaveAssinatura) {
        if (versaoChaveAssinatura == null) {
            throw new NullPointerException(
                "A versão da chave utilizada para assinar o conteúdo do QR code deve ser informada."
            );
        }

        this.versaoChaveAssinatura = DataTypeConverter.toInteger(versaoChaveAssinatura);
    }
}
