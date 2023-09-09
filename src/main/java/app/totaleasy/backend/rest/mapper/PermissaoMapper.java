package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.PermissaoRetrievalDTO;
import app.totaleasy.backend.rest.model.Permissao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissaoMapper {

    public PermissaoRetrievalDTO toPermissaoRetrievalDTO(Permissao permissao) {
        return new PermissaoRetrievalDTO(
            permissao.getId(),
            permissao.getNome(),
            permissao.getDescricao()
        );
    }
}
