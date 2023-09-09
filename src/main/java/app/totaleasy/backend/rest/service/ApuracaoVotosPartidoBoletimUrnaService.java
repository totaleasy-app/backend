package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.id.CargoEleicaoIdDTO;
import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import app.totaleasy.backend.rest.model.ApuracaoVotosPartidoBoletimUrna;
import app.totaleasy.backend.rest.repository.ApuracaoVotosPartidoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "apuracao-votos-partido-boletim-urna")
public class ApuracaoVotosPartidoBoletimUrnaService {

    private final ApuracaoVotosPartidoBoletimUrnaRepository apuracaoVotosPartidoBoletimUrnaRepository;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    private final CachingService cachingService;

    @Cacheable(key = "T(java.lang.String).format('%d:%d:%d:%d:%d:%s:%d', #id.numeroTSEPartido, #id.codigoTSECargo, #id.codigoTSEEleicao, #id.numeroTSESecao, #id.numeroTSEZona, #id.siglaUF, #id.codigoTSEPleito)")
    public ApuracaoVotosPartidoBoletimUrna findById(ApuracaoVotosPartidoBoletimUrnaIdDTO id) {
        return this.apuracaoVotosPartidoBoletimUrnaRepository
            .findByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
                id.getNumeroTSEPartido(),
                id.getCodigoTSECargo(),
                id.getCodigoTSEEleicao(),
                id.getNumeroTSESecao(),
                id.getNumeroTSEZona(),
                id.getSiglaUF(),
                id.getCodigoTSEPleito()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma apuração de votos de partido por boletim de urna identificada por %s.", id));
            });
    }

    @Cacheable(key = "#root.methodName")
    public List<ApuracaoVotosPartidoBoletimUrna> findAll() {
        return this.apuracaoVotosPartidoBoletimUrnaRepository.findAll();
    }

    public ApuracaoVotosPartidoBoletimUrna getIfExistsOrElseSave(ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna) {
        if (this.apuracaoVotosPartidoBoletimUrnaRepository.existsByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            apuracaoVotosPartidoBoletimUrna.getPartido().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getCargoEleicao().getCargo().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getCargoEleicao().getEleicao().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE()
        )) {
            return this.findById(this.apuracaoVotosPartidoBoletimUrnaMapper.toApuracaoVotosPartidoBoletimUrnaIdDTO(apuracaoVotosPartidoBoletimUrna));
        }

        this.cachingService.evictAllCaches();

        return this.apuracaoVotosPartidoBoletimUrnaRepository.save(apuracaoVotosPartidoBoletimUrna);
    }

    public void deleteById(ApuracaoVotosPartidoBoletimUrnaIdDTO id) {
        id.validate();

        if (!this.apuracaoVotosPartidoBoletimUrnaRepository.existsByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            id.getNumeroTSEPartido(),
            id.getCodigoTSECargo(),
            id.getCodigoTSEEleicao(),
            id.getNumeroTSESecao(),
            id.getNumeroTSEZona(),
            id.getSiglaUF(),
            id.getCodigoTSEPleito()
        )) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma apuração de votos de partido por boletim de urna identificada por %s.", id));
        }

        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            id.getNumeroTSEPartido(),
            id.getCodigoTSECargo(),
            id.getCodigoTSEEleicao(),
            id.getNumeroTSESecao(),
            id.getNumeroTSEZona(),
            id.getSiglaUF(),
            id.getCodigoTSEPleito()
        );

        this.cachingService.evictAllCaches();
    }

    public void deleteByPartido(Integer numeroTSEPartido) {
        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByPartidoNumeroTSE(numeroTSEPartido);

        this.cachingService.evictAllCaches();
    }

    public void deleteByCargoEleicao(CargoEleicaoIdDTO cargoEleicaoIdDTO) {
        cargoEleicaoIdDTO.validate();

        this.apuracaoVotosPartidoBoletimUrnaRepository
            .deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
                cargoEleicaoIdDTO.getCodigoTSECargo(),
                cargoEleicaoIdDTO.getCodigoTSEEleicao()
            );

        this.cachingService.evictAllCaches();
    }

    public void deleteByBoletimUrna(BoletimUrnaIdDTO boletimUrnaId) {
        boletimUrnaId.validate();

        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            boletimUrnaId.getNumeroTSESecao(),
            boletimUrnaId.getNumeroTSEZona(),
            boletimUrnaId.getSiglaUF(),
            boletimUrnaId.getCodigoTSEPleito()
        );

        this.cachingService.evictAllCaches();
    }
}
