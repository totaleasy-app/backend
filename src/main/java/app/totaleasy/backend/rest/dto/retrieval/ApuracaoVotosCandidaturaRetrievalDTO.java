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
    "numeroCandidato", "nomeCandidato", "codigoCargo", "nomeCargo", "codigoEleicao",
    "nomeEleicao", "codigoPleito", "turno", "totalVotosApurados"
})
public class ApuracaoVotosCandidaturaRetrievalDTO implements Serializable {

    private Integer numeroCandidato;

    private String nomeCandidato;

    private Integer codigoCargo;

    private String nomeCargo;

    private Integer codigoEleicao;

    private String nomeEleicao;

    private Integer codigoPleito;

    private Integer turno;

    private Long totalVotosApurados;
}
