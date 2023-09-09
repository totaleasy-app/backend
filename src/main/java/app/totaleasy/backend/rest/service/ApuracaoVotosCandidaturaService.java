package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import app.totaleasy.backend.rest.repository.ApuracaoVotosCandidaturaBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "apuracao-votos-candidatura")
public class ApuracaoVotosCandidaturaService {

    private final ApuracaoVotosCandidaturaBoletimUrnaRepository apuracaoVotosCandidaturaBoletimUrnaRepository;

    @Cacheable(key = "#root.methodName")
    public List<ApuracaoVotosCandidaturaRetrievalDTO> findAll() {
        return this.apuracaoVotosCandidaturaBoletimUrnaRepository.findApuracaoVotosCandidaturas();
    }
}
