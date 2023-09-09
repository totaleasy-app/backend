package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Cargo;
import app.totaleasy.backend.rest.model.CargoEleicao;
import app.totaleasy.backend.rest.model.Eleicao;
import app.totaleasy.backend.rest.repository.EleicaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "eleicao")
public class EleicaoService {

    private final EleicaoRepository eleicaoRepository;

    private final CargoEleicaoService cargoEleicaoService;

    private final CachingService cachingService;

    @Autowired
    public EleicaoService(
        EleicaoRepository eleicaoRepository,
        @Lazy CargoEleicaoService cargoEleicaoService,
        CachingService cachingService
    ) {
        this.eleicaoRepository = eleicaoRepository;
        this.cargoEleicaoService = cargoEleicaoService;
        this.cachingService = cachingService;
    }

    @Cacheable(key = "#codigoTSE")
    public Eleicao findByCodigoTSE(Integer codigoTSE) {
        return this.eleicaoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma eleição com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Eleicao> findAll() {
        return this.eleicaoRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<Cargo> findCargos(Integer codigoTSE) {
        return this
            .findByCodigoTSE(codigoTSE)
            .getCargos()
            .stream()
            .map(CargoEleicao::getCargo)
            .collect(Collectors.toSet());
    }

    public Eleicao getIfExistsOrElseSave(Eleicao eleicao) {
        if (this.eleicaoRepository.existsByCodigoTSE(eleicao.getCodigoTSE())) {
            return this.findByCodigoTSE(eleicao.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.eleicaoRepository.save(eleicao);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.eleicaoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma eleição com o código %d.", codigoTSE));
        }

        this.cargoEleicaoService.deleteByEleicao(codigoTSE);

        this.eleicaoRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }

    public void deleteByPleito(Integer codigoTSEPleito) {
        this.eleicaoRepository
            .findByPleitoCodigoTSE(codigoTSEPleito)
            .forEach(eleicao -> this.cargoEleicaoService.deleteByEleicao(eleicao.getCodigoTSE()));

        this.eleicaoRepository.deleteByPleitoCodigoTSE(codigoTSEPleito);

        this.cachingService.evictAllCaches();
    }
}
