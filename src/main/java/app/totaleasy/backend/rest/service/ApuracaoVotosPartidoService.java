package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoRetrievalDTO;
import app.totaleasy.backend.rest.repository.ApuracaoVotosPartidoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "apuracao-votos-partido")
public class ApuracaoVotosPartidoService {

    private final ApuracaoVotosPartidoBoletimUrnaRepository apuracaoVotosPartidoBoletimUrnaRepository;

    @Cacheable(key = "#root.methodName")
    public List<ApuracaoVotosPartidoRetrievalDTO> findAll() {
        return this.apuracaoVotosPartidoBoletimUrnaRepository.findApuracaoVotosPartidos();
    }
}
