package app.totaleasy.backend.rest.service;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.BoletimUrna;
import app.totaleasy.backend.rest.model.Fase;
import app.totaleasy.backend.rest.repository.FaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "fase")
public class FaseService {

    private final FaseRepository faseRepository;

    private final BoletimUrnaService boletimUrnaService;

    private final CachingService cachingService;

    @Cacheable(key = "#nome")
    public Fase findByNome(String nome) {
        return this.faseRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma fase com o nome '%s'.", nome)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Fase> findAll() {
        return this.faseRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nome)")
    public Set<BoletimUrna> findBoletinsUrna(String nome) {
        return this.findByNome(nome).getBoletinsUrna();
    }

    public Fase getIfExistsOrElseSave(Fase fase) {
        if (this.faseRepository.existsByNomeEqualsIgnoreCase(fase.getNome())) {
            return this.findByNome(fase.getNome());
        }

        this.cachingService.evictAllCaches();

        return this.faseRepository.save(fase);
    }

    public void deleteByNome(String nome) {
        if (!this.faseRepository.existsByNomeEqualsIgnoreCase(nome)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma fase com o nome '%s'.", nome));
        }

        this.boletimUrnaService.deleteByFase(nome);

        this.faseRepository.deleteByNomeEqualsIgnoreCase(nome);

        this.cachingService.evictAllCaches();
    }
}
