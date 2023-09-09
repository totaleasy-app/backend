package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO;
import app.totaleasy.backend.rest.repository.ApuracaoVotosCargoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "apuracao-votos-cargo")
public class ApuracaoVotosCargoService {

    private final ApuracaoVotosCargoBoletimUrnaRepository apuracaoVotosCargoBoletimUrnaRepository;

    @Cacheable(key = "#root.methodName")
    public List<ApuracaoVotosCargoRetrievalDTO> findAll() {
        return this.apuracaoVotosCargoBoletimUrnaRepository.findApuracoesVotosCargos();
    }
}
