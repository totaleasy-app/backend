package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.LocalVotacao;
import app.totaleasy.backend.rest.model.Municipio;
import app.totaleasy.backend.rest.repository.MunicipioRepository;

import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "municipio")
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    private final LocalVotacaoService localVotacaoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public Municipio findByCodigoTSE(Integer codigoTSE) {
        return this.municipioRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum município com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Municipio> findAll() {
        return this.municipioRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<LocalVotacao> findLocaisVotacao(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getLocaisVotacao();
    }

    public Municipio getIfExistsOrElseSave(@NotNull Municipio municipio) {
        if (this.municipioRepository.existsByCodigoTSE(municipio.getCodigoTSE())) {
            return this.findByCodigoTSE(municipio.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.municipioRepository.save(municipio);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.municipioRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum município com o código %d.", codigoTSE));
        }

        this.localVotacaoService.deleteByMunicipio(codigoTSE);

        this.municipioRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }
}
