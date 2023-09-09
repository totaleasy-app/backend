package app.totaleasy.backend.rest.dto.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {
    "id", "numeroTSE", "codigoTSE", "nomeCompleto", "nomeUrna",
    "nomeSocial", "numeroCPF", "numeroTituloEleitoral", "dataNascimento",
    "genero", "corRaca", "grauInstrucao", "estadoCivil", "ocupacao"
})
public class CandidatoRetrievalDTO {

    private Integer id;

    private Integer numeroTSE;

    private String codigoTSE;

    private String nomeCompleto;

    private String nomeUrna;

    private String nomeSocial;

    private String numeroCPF;

    private String numeroTituloEleitoral;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private GeneroRetrievalDTO genero;

    private CorRacaRetrievalDTO corRaca;

    private GrauInstrucaoRetrievalDTO grauInstrucao;

    private EstadoCivilRetrievalDTO estadoCivil;

    private OcupacaoRetrievalDTO ocupacao;
}
