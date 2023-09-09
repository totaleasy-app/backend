package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.BoletimUrna;
import app.totaleasy.backend.rest.model.OrigemBoletimUrna;
import app.totaleasy.backend.rest.repository.OrigemBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "origem-boletim-urna")
public class OrigemBoletimUrnaService {

    private final OrigemBoletimUrnaRepository origemBoletimUrnaRepository;

    private final BoletimUrnaService boletimUrnaService;

    private final CachingService cachingService;

    @Cacheable(key = "#nomeAbreviado")
    public OrigemBoletimUrna findByNomeAbreviado(String nomeAbreviado) {
        return this.origemBoletimUrnaRepository
            .findByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado)
            .orElseThrow(() -> {
                throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma origem de boletim de urna com o nome abreviado '%s'.", nomeAbreviado));
            });
    }

    @Cacheable(key = "#root.methodName")
    public List<OrigemBoletimUrna> findAll() {
        return this.origemBoletimUrnaRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nomeAbreviado)")
    public Set<BoletimUrna> findBoletinsUrna(String nomeAbreviado) {
        return this.findByNomeAbreviado(nomeAbreviado).getBoletinsUrna();
    }

    public OrigemBoletimUrna getIfExistsOrElseSave(OrigemBoletimUrna origemBoletimUrna) {
        if (this.origemBoletimUrnaRepository.existsByNomeAbreviadoEqualsIgnoreCase(origemBoletimUrna.getNomeAbreviado())) {
            return this.findByNomeAbreviado(origemBoletimUrna.getNomeAbreviado());
        }

        this.cachingService.evictAllCaches();

        return this.origemBoletimUrnaRepository.save(origemBoletimUrna);
    }

    public void deleteByNomeAbreviado(String nomeAbreviado) {
        if (!this.origemBoletimUrnaRepository.existsByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma origem de boletim de urna com o nome abreviado '%s'.", nomeAbreviado));
        }

        this.boletimUrnaService.deleteByOrigemBoletimUrna(nomeAbreviado);

        this.origemBoletimUrnaRepository.deleteByNomeAbreviadoEqualsIgnoreCase(nomeAbreviado);

        this.cachingService.evictAllCaches();
    }
}
