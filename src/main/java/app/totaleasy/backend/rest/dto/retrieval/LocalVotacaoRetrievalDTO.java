package app.totaleasy.backend.rest.dto.retrieval;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "numeroTSE", "nome", "zona", "municipio"})
public class LocalVotacaoRetrievalDTO {

    private Integer id;

    private Integer numeroTSE;

    private String nome;

    private ZonaRetrievalDTO zona;

    private MunicipioRetrievalDTO municipio;
}
