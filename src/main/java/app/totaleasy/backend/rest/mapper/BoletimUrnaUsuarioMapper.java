package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.id.BoletimUrnaUsuarioIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.BoletimUrnaUsuarioRetrievalDTO;
import app.totaleasy.backend.rest.model.BoletimUrnaUsuario;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoletimUrnaUsuarioMapper {

    private final UsuarioMapper usuarioMapper;

    private final BoletimUrnaMapper boletimUrnaMapper;

    public BoletimUrnaUsuarioRetrievalDTO toBoletimUrnaUsuarioRetrievalDTO(BoletimUrnaUsuario boletimUrnaUsuario) {
        return new BoletimUrnaUsuarioRetrievalDTO(
            boletimUrnaUsuario.getId(),
            this.usuarioMapper.toUsuarioRetrievalDTO(boletimUrnaUsuario.getUsuario()),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(boletimUrnaUsuario.getBoletimUrna()),
            boletimUrnaUsuario.getColetadoEm(),
            boletimUrnaUsuario.getAtualizadoEm()
        );
    }

    public BoletimUrnaUsuarioIdDTO toBoletimUrnaUsuarioIdDTO(BoletimUrnaUsuario boletimUrnaUsuario) {
        return new BoletimUrnaUsuarioIdDTO(
            boletimUrnaUsuario.getUsuario().getUsername(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE()
        );
    }
}
