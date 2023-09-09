package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.OrigemConfiguracaoProcessoEleitoral;
import app.totaleasy.backend.rest.model.ProcessoEleitoral;
import app.totaleasy.backend.rest.repository.OrigemConfiguracaoProcessoEleitoralRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "origem-configuracao-processo-eleitoral")
public class OrigemConfiguracaoProcessoEleitoralService {

    private final OrigemConfiguracaoProcessoEleitoralRepository origemConfiguracaoProcessoEleitoralRepository;

    private final ProcessoEleitoralService processoEleitoralService;

    private final CachingService cachingService;

    @Cacheable(key = "#nomeAbreviado")
    public OrigemConfiguracaoProcessoEleitoral findByNomeAbreviado(String nomeAbreviado) {
        return this.origemConfiguracaoProcessoEleitoralRepository
            .findByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado)
            .orElseThrow(() -> {
                throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma origem de configuração de processo eleitoral com o nome abreviado '%s'.", nomeAbreviado));
            });
    }

    @Cacheable(key = "#root.methodName")
    public List<OrigemConfiguracaoProcessoEleitoral> findAll() {
        return this.origemConfiguracaoProcessoEleitoralRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nomeAbreviado)")
    public Set<ProcessoEleitoral> findProcessosEleitorais(String nomeAbreviado) {
        return this.findByNomeAbreviado(nomeAbreviado).getProcessosEleitorais();
    }

    public OrigemConfiguracaoProcessoEleitoral getIfExistsOrElseSave(OrigemConfiguracaoProcessoEleitoral origemConfiguracaoProcessoEleitoral) {
        if (this.origemConfiguracaoProcessoEleitoralRepository.existsByNomeAbreviadoEqualsIgnoreCase(origemConfiguracaoProcessoEleitoral.getNomeAbreviado())) {
            return this.findByNomeAbreviado(origemConfiguracaoProcessoEleitoral.getNomeAbreviado());
        }

        this.cachingService.evictAllCaches();

        return this.origemConfiguracaoProcessoEleitoralRepository.save(origemConfiguracaoProcessoEleitoral);
    }

    public void deleteByNomeAbreviado(String nomeAbreviado) {
        if (!this.origemConfiguracaoProcessoEleitoralRepository.existsByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma origem de configuração de processo eleitoral com o nome abreviado '%s'.", nomeAbreviado));
        }

        this.processoEleitoralService.deleteByOrigemConfiguracao(nomeAbreviado);

        this.origemConfiguracaoProcessoEleitoralRepository.deleteByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado);

        this.cachingService.evictAllCaches();
    }
}
