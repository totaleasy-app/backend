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
@JsonPropertyOrder(value = {"id", "papel", "usuario"})
public class PapelUsuarioRetrievalDTO {

    private Integer id;

    private PapelRetrievalDTO papel;

    private UsuarioRetrievalDTO usuario;
}
