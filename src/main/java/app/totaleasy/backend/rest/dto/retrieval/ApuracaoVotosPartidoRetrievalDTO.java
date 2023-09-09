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
    "numeroPartido", "nomePartido", "codigoCargo", "nomeCargo", "codigoEleicao", "nomeEleicao",
    "codigoPleito", "turno", "quantidadeVotosDeLegenda", "totalVotosApurados"
})
public class ApuracaoVotosPartidoRetrievalDTO implements Serializable {

    private Integer numeroPartido;

    private String nomePartido;

    private Integer codigoCargo;

    private String nomeCargo;

    private Integer codigoEleicao;

    private String nomeEleicao;

    private Integer codigoPleito;

    private Integer turno;

    private Long quantidadeVotosDeLegenda;

    private Long totalVotosApurados;
}
