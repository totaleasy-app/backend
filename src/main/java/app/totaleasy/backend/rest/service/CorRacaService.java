package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.CorRaca;
import app.totaleasy.backend.rest.repository.CorRacaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "cor-raca")
public class CorRacaService {

    private final CorRacaRepository corRacaRepository;

    private final CandidatoService candidatoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public CorRaca findByCodigoTSE(Integer codigoTSE) {
        return this.corRacaRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma cor/raça com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<CorRaca> findAll() {
        return this.corRacaRepository.findAll();
    }

    public Set<Candidato> findCandidatos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidatos();
    }

    public CorRaca getIfExistsOrElseSave(CorRaca corRaca) {
        if (this.corRacaRepository.existsByCodigoTSE(corRaca.getCodigoTSE())) {
            return this.findByCodigoTSE(corRaca.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.corRacaRepository.save(corRaca);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.corRacaRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma cor/raça com o código %d.", codigoTSE));
        }

        this.candidatoService.deleteByCorRaca(codigoTSE);

        this.corRacaRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }
}
