package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Candidato;
import app.totaleasy.backend.rest.model.GrauInstrucao;
import app.totaleasy.backend.rest.repository.GrauInstrucaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "grau-instrucao")
public class GrauInstrucaoService {

    private final GrauInstrucaoRepository grauInstrucaoRepository;

    private final CandidatoService candidatoService;

    private final CachingService cachingService;

    @Cacheable(key = "#codigoTSE")
    public GrauInstrucao findByCodigoTSE(Integer codigoTSE) {
        return this.grauInstrucaoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum grau de instrução com o código %d.", codigoTSE)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<GrauInstrucao> findAll() {
        return this.grauInstrucaoRepository.findAll();
    }

    public Set<Candidato> findCandidatos(Integer codigoTSE) {
        return this.findByCodigoTSE(codigoTSE).getCandidatos();
    }

    public GrauInstrucao getIfExistsOrElseSave(GrauInstrucao grauInstrucao) {
        if (this.grauInstrucaoRepository.existsByCodigoTSE(grauInstrucao.getCodigoTSE())) {
            return this.findByCodigoTSE(grauInstrucao.getCodigoTSE());
        }

        this.cachingService.evictAllCaches();

        return this.grauInstrucaoRepository.save(grauInstrucao);
    }

    public void deleteByCodigoTSE(Integer codigoTSE) {
        if (!this.grauInstrucaoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum grau de instrução com o código %d.", codigoTSE));
        }

        this.candidatoService.deleteByGrauInstrucao(codigoTSE);

        this.grauInstrucaoRepository.deleteByCodigoTSE(codigoTSE);

        this.cachingService.evictAllCaches();
    }
}
