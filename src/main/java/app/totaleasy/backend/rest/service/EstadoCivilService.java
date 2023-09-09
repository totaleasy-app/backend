package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.EstadoCivil;
import app.totaleasy.backend.rest.repository.EstadoCivilRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "estado-civil")
public class EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    private final CandidatoService candidatoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public EstadoCivil findByCodigoTSE(Integer codigoTSE) {
        return this.estadoCivilRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum estado civil com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<EstadoCivil> findAll() {
        return this.estadoCivilRepository.findAll();
    }

    public Set<Candidato> findCandidatos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidatos();
    }

    public EstadoCivil getIfExistsOrElseSave(EstadoCivil estadoCivil) {
        if (this.estadoCivilRepository.existsByCodigoTSE(estadoCivil.getCodigoTSE())) {
            return this.findByCodigoTSE(estadoCivil.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.estadoCivilRepository.save(estadoCivil);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.estadoCivilRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum estado civil com o código %d.", codigoTSE));
        }

        this.candidatoService.deleteByEstadoCivil(codigoTSE);

        this.estadoCivilRepository.deleteByCodigoTSE(codigoTSE);
        
        this.cachingService.evictAllCaches();
    }
}
