package app.totaleasy.backend.rest.mapper;

import app.totaleasy.backend.rest.dto.retrieval.UrnaEletronicaRetrievalDTO;
import app.totaleasy.backend.rest.model.UrnaEletronica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrnaEletronicaMapper {

    public UrnaEletronicaRetrievalDTO toUrnaEletronicaRetrievalDTO(UrnaEletronica urnaEletronica) {
        return new UrnaEletronicaRetrievalDTO(
            urnaEletronica.getId(),
            urnaEletronica.getNumeroSerie(),
            urnaEletronica.getCodigoIdentificacaoCarga(),
            urnaEletronica.getVersaoSoftware(),
            urnaEletronica.getDataAbertura(),
            urnaEletronica.getHorarioAbertura(),
            urnaEletronica.getDataFechamento(),
            urnaEletronica.getHorarioFechamento()
        );
    }
}
