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
@JsonPropertyOrder(value = {"id", "codigoTSE", "nome", "nomeAbreviado", "tipo"})
public class CargoRetrievalDTO {

    private Integer id;

    private Integer codigoTSE;

    private String nome;

    private String nomeAbreviado;

    private TipoCargoRetrievalDTO tipo;
}
