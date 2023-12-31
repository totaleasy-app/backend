package app.totaleasy.backend.rest.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.AgregacaoSecao;
import app.totaleasy.backend.rest.model.Pleito;
import app.totaleasy.backend.rest.model.ProcessoEleitoral;
import app.totaleasy.backend.rest.model.Secao;
import app.totaleasy.backend.rest.model.SecaoProcessoEleitoral;
import app.totaleasy.backend.rest.repository.ProcessoEleitoralRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "processo-eleitoral")
public class ProcessoEleitoralService {

    private final ProcessoEleitoralRepository processoEleitoralRepository;

    private final PleitoService pleitoService;

    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    private final AgregacaoSecaoService agregacaoSecaoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public ProcessoEleitoral findByCodigoTSE(Integer codigoTSE) {
        return this.processoEleitoralRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum processo eleitoral com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<ProcessoEleitoral> findAll() {
        return this.processoEleitoralRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<Pleito> findPleitos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getPleitos();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<Secao> findSecoes(Integer codigoTSE) {
        return this
            .findByCodigoTSE(codigoTSE)
            .getSecoes()
            .stream()
            .map(SecaoProcessoEleitoral::getSecao)
            .collect(Collectors.toSet());
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<AgregacaoSecao> findSecoesAgregadas(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getSecoesAgregadas();
    }

    public ProcessoEleitoral getIfExistsOrElseSave(ProcessoEleitoral processoEleitoral) {
        if (this.processoEleitoralRepository.existsByCodigoTSE(processoEleitoral.getCodigoTSE())) {
            return this.findByCodigoTSE(processoEleitoral.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.processoEleitoralRepository.save(processoEleitoral);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.processoEleitoralRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum processo eleitoral com o código %d.", codigoTSE));
        }

        this.pleitoService.deleteByProcessoEleitoral(codigoTSE);
        this.secaoProcessoEleitoralService.deleteByProcessoEleitoral(codigoTSE);
        this.agregacaoSecaoService.deleteByProcessoEleitoral(codigoTSE);

        this.processoEleitoralRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }

    public void deleteByOrigemConfiguracao(String nomeAbreviadoOrigemConfiguracao) {
        this.processoEleitoralRepository
            .findByOrigemConfiguracaoNomeAbreviadoEqualsIgnoreCase(nomeAbreviadoOrigemConfiguracao)
            .forEach(processoEleitoral -> {
                this.pleitoService.deleteByProcessoEleitoral(processoEleitoral.getCodigoTSE());
                this.secaoProcessoEleitoralService.deleteByProcessoEleitoral(processoEleitoral.getCodigoTSE());
                this.agregacaoSecaoService.deleteByProcessoEleitoral(processoEleitoral.getCodigoTSE());
            });

        this.processoEleitoralRepository.deleteByOrigemConfiguracaoNomeAbreviadoEqualsIgnoreCase(nomeAbreviadoOrigemConfiguracao);

        this.cachingService.evictAllCaches();
    }
}
