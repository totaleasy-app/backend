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
@JsonPropertyOrder(value = {"id", "codigoIBGE", "nome", "sigla"})
public class RegiaoRetrievalDTO {

    private Integer id;

    private Integer codigoIBGE;

    private String nome;

    private String sigla;
}
