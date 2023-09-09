package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.Ocupacao;
import app.totaleasy.backend.rest.repository.OcupacaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "ocupacao")
public class OcupacaoService {

    private final OcupacaoRepository ocupacaoRepository;

    private final CandidatoService candidatoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public Ocupacao findByCodigoTSE(Integer codigoTSE) {
        return this.ocupacaoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma ocupação com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Ocupacao> findAll() {
        return this.ocupacaoRepository.findAll();
    }

    public Set<Candidato> findCandidatos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidatos();
    }

    public Ocupacao getIfExistsOrElseSave(Ocupacao ocupacao) {
        if (this.ocupacaoRepository.existsByCodigoTSE(ocupacao.getCodigoTSE())) {
            return this.findByCodigoTSE(ocupacao.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.ocupacaoRepository.save(ocupacao);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.ocupacaoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma ocupação com o código %d.", codigoTSE));
        }

        this.candidatoService.deleteByOcupacao(codigoTSE);

        this.ocupacaoRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }
}
