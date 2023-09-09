package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.Genero;
import app.totaleasy.backend.rest.repository.GeneroRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "genero")
public class GeneroService {

    private final GeneroRepository generoRepository;

    private final CandidatoService candidatoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public Genero findByCodigoTSE(Integer codigoTSE) {
        return this.generoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum gênero com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Genero> findAll() {
        return this.generoRepository.findAll();
    }

    public Set<Candidato> findCandidatos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidatos();
    }

    public Genero getIfExistsOrElseSave(Genero genero) {
        if (this.generoRepository.existsByCodigoTSE(genero.getCodigoTSE())) {
            return this.findByCodigoTSE(genero.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.generoRepository.save(genero);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.generoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum gênero com o código %d.", codigoTSE));
        }

        this.candidatoService.deleteByGenero(codigoTSE);

        this.generoRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }
}
