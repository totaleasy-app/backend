package app.totaleasy.backend.core.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class URLConfiguration {

    private String urlVerificacaoAssinatura;

    private String urlDadosComplementares;

    public String getURLVerificacaoAssinatura() {
        return this.urlVerificacaoAssinatura;
    }

    public void setURLVerificacaoAssinatura(String urlVerificacaoAssinatura) {
        this.urlVerificacaoAssinatura = urlVerificacaoAssinatura;
    }

    public String getURLDadosComplementares() {
        return this.urlDadosComplementares;
    }

    public void setURLDadosComplementares(String urlDadosComplementares) {
        this.urlDadosComplementares = urlDadosComplementares;
    }
}
