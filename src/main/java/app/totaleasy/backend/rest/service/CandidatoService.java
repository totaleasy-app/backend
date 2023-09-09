package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.Candidatura;
import app.totaleasy.backend.rest.repository.CandidatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "candidato")
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    private final CandidaturaService candidaturaService;

    private final CachingService cachingService;

    @Autowired
    public CandidatoService(
        CandidatoRepository candidatoRepository,
        @Lazy CandidaturaService candidaturaService,
        CachingService cachingService
    ) {
        this.candidatoRepository = candidatoRepository;
        this.candidaturaService = candidaturaService;
        this.cachingService = cachingService;
    }

    @Cacheable(key = "#codigoTSE")
    public Candidato findByCodigoTSE(String codigoTSE) {
        return this.candidatoRepository
            .findByCodigoTSEEqualsIgnoreCase(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum candidato com o código %s.", codigoTSE));
            });
    }

    @Cacheable(key = "#root.methodName")
    public List<Candidato> findAll() {
        return this.candidatoRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d', #root.methodName, #codigoTSE)")
    public Set<Candidatura> findCandidaturas(String codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidaturas();
    }

    public Candidato getIfExistsOrElseSave(Candidato candidato) {
        if (this.candidatoRepository.existsByCodigoTSEEqualsIgnoreCase(candidato.getCodigoTSE())) {
            return this.findByCodigoTSE(candidato.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.candidatoRepository.save(candidato);
    }

    public void deleteByCodigoTSE(String codigoTSE) {
        if (!this.candidatoRepository.existsByCodigoTSEEqualsIgnoreCase(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum candidato com o código %s.", codigoTSE));
        }

        this.candidaturaService.deleteByCandidato(codigoTSE);

        this.candidatoRepository.deleteByCodigoTSEEqualsIgnoreCase(codigoTSE);

        this.cachingService.evictAllCaches();
    }

    public void deleteByGenero(Integer codigoTSEGenero) {
        this.candidatoRepository
            .findByGeneroCodigoTSE(codigoTSEGenero)
            .forEach(candidato -> this.candidaturaService.deleteByCandidato(candidato.getCodigoTSE()));

        this.candidatoRepository.deleteByGeneroCodigoTSE(codigoTSEGenero);

        this.cachingService.evictAllCaches();
    }

    public void deleteByCorRaca(Integer codigoTSECorRaca) {
        this.candidatoRepository
            .findByCorRacaCodigoTSE(codigoTSECorRaca)
            .forEach(candidato -> this.candidaturaService.deleteByCandidato(candidato.getCodigoTSE()));

        this.candidatoRepository.deleteByCorRacaCodigoTSE(codigoTSECorRaca);

        this.cachingService.evictAllCaches();
    }

    public void deleteByGrauInstrucao(Integer codigoTSEGrauInstrucao) {
        this.candidatoRepository
            .findByGrauInstrucaoCodigoTSE(codigoTSEGrauInstrucao)
            .forEach(candidato -> this.candidaturaService.deleteByCandidato(candidato.getCodigoTSE()));

        this.candidatoRepository.deleteByGrauInstrucaoCodigoTSE(codigoTSEGrauInstrucao);

        this.cachingService.evictAllCaches();
    }

    public void deleteByEstadoCivil(Integer codigoTSEEstadoCivil) {
        this.candidatoRepository
            .findByEstadoCivilCodigoTSE(codigoTSEEstadoCivil)
            .forEach(candidato -> this.candidaturaService.deleteByCandidato(candidato.getCodigoTSE()));

        this.candidatoRepository.deleteByEstadoCivilCodigoTSE(codigoTSEEstadoCivil);

        this.cachingService.evictAllCaches();
    }

    public void deleteByOcupacao(Integer codigoTSEOcupacao) {
        this.candidatoRepository
            .findByOcupacaoCodigoTSE(codigoTSEOcupacao)
            .forEach(candidato -> this.candidaturaService.deleteByCandidato(candidato.getCodigoTSE()));

        this.candidatoRepository.deleteByOcupacaoCodigoTSE(codigoTSEOcupacao);

        this.cachingService.evictAllCaches();
    }
}
