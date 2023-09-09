package app.totaleasy.backend.rest.dto.retrieval;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {
    "codigoCargo", "nomeCargo", "codigoEleicao", "nomeEleicao", "codigoPleito",
    "turno", "quantidadeVotosNominais", "quantidadeVotosDeLegenda",
    "quantidadeVotosEmBranco", "quantidadeVotosNulos", "totalVotosApurados"
})
public class ApuracaoVotosCargoRetrievalDTO implements Serializable {

    private Integer codigoCargo;

    private String nomeCargo;

    private Integer codigoEleicao;

    private String nomeEleicao;

    private Integer codigoPleito;

    private Integer turno;

    private Long quantidadeVotosNominais;

    private Long quantidadeVotosDeLegenda;

    private Long quantidadeVotosEmBranco;

    private Long quantidadeVotosNulos;

    private Long totalVotosApurados;
}
