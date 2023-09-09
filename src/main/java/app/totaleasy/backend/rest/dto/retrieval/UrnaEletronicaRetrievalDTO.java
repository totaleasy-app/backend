package app.totaleasy.backend.rest.dto.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {
    "id", "numeroSerie", "codigoIdentificacaoCarga",
    "versaoSoftware", "dataAbertura", "horarioAbertura",
    "dataFechamento", "horarioFechamento"
})
public class UrnaEletronicaRetrievalDTO {

    private Integer id;

    private Integer numeroSerie;

    private String codigoIdentificacaoCarga;

    private String versaoSoftware;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioAbertura;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioFechamento;
}
