package app.totaleasy.backend.core.model.complemento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class BoletimUrna {

    private String assinatura;

    private ProcessoEleitoral processoEleitoral;
}
