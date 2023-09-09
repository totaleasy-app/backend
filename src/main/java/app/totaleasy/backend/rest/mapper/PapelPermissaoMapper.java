package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.PapelPermissaoRetrievalDTO;
import app.totaleasy.backend.rest.model.PapelPermissao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PapelPermissaoMapper {

    private final PapelMapper papelMapper;

    private final PermissaoMapper permissaoMapper;

    public PapelPermissaoRetrievalDTO toPapelPermissaoRetrievalDTO(PapelPermissao papelPermissao) {
        return new PapelPermissaoRetrievalDTO(
            papelPermissao.getId(),
            this.papelMapper.toPapelRetrievalDTO(papelPermissao.getPapel()),
            this.permissaoMapper.toPermissaoRetrievalDTO(papelPermissao.getPermissao())
        );
    }
}
