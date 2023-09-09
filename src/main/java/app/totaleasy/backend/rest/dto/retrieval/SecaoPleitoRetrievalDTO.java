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
@JsonPropertyOrder(value = {
    "id", "secao", "pleito", "quantidadeEleitoresAptos", "quantidadeEleitoresComparecentes",
    "quantidadeEleitoresFaltosos", "quantidadeEleitoresHabilitadosPorAnoNascimento"
})
public class SecaoPleitoRetrievalDTO {

    private Integer id;

    private SecaoRetrievalDTO secao;

    private PleitoRetrievalDTO pleito;

    private Integer quantidadeEleitoresAptos;

    private Integer quantidadeEleitoresComparecentes;

    private Integer quantidadeEleitoresFaltosos;

    private Integer quantidadeEleitoresHabilitadosPorAnoNascimento;
}
