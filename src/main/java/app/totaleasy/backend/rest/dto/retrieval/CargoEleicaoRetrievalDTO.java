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
@JsonPropertyOrder(value = {"id", "cargo", "eleicao"})
public class CargoEleicaoRetrievalDTO {

    private Integer id;

    private CargoRetrievalDTO cargo;

    private EleicaoRetrievalDTO eleicao;
}
