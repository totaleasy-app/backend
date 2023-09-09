package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.PapelUsuarioRetrievalDTO;
import app.totaleasy.backend.rest.model.PapelUsuario;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PapelUsuarioMapper {

    private final PapelMapper papelMapper;

    private final UsuarioMapper usuarioMapper;

    public PapelUsuarioRetrievalDTO toPapelUsuarioRetrievalDTO(PapelUsuario papelUsuario) {
        return new PapelUsuarioRetrievalDTO(
            papelUsuario.getId(),
            this.papelMapper.toPapelRetrievalDTO(papelUsuario.getPapel()),
            this.usuarioMapper.toUsuarioRetrievalDTO(papelUsuario.getUsuario())
        );
    }
}
