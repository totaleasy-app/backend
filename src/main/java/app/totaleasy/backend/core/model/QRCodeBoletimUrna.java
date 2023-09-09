package app.totaleasy.backend.core.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class QRCodeBoletimUrna {

    private CabecalhoQRCodeBoletimUrna cabecalho;

    private ConteudoQRCodeBoletimUrna conteudo;

    private String payload;

    private String hash;

    private String assinatura;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final List<String> CHAVES_CABECALHO = List.of("QRBU", "VRQR", "VRCH");

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final List<String> CHAVES_SEGURANCA = List.of("HASH", "ASSI");

    @Override
    public int hashCode() {
        return Objects.hash(this.hash);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        QRCodeBoletimUrna qrCodeBoletimUrna = (QRCodeBoletimUrna) object;

        return Objects.equals(this.hash, qrCodeBoletimUrna.hash);
    }

    public String getPayloadCabecalho() {
        List<String> payloadCabecalho = new LinkedList<>();

        for (String registro : this.payload.split(" ")) {
            String chave = registro.split(":", 2)[0].toUpperCase();

            if (CHAVES_CABECALHO.contains(chave)) {
                payloadCabecalho.add(registro);
            }
        }

        return String.join(" ", payloadCabecalho);
    }

    public String getPayloadConteudo() {
        List<String> payloadConteudo = new LinkedList<>();

        for (String registro : this.payload.split(" ")) {
            String chave = registro.split(":", 2)[0].toUpperCase();

            if (!CHAVES_CABECALHO.contains(chave) && !CHAVES_SEGURANCA.contains(chave)) {
                payloadConteudo.add(registro);
            }
        }

        return String.join(" ", payloadConteudo);
    }
}
