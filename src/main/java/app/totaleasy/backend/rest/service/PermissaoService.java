package app.totaleasy.backend.rest.service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Papel;
import app.totaleasy.backend.rest.model.PapelPermissao;
import app.totaleasy.backend.rest.model.Permissao;
import app.totaleasy.backend.rest.repository.PermissaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "permissao")
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    @Cacheable(key = "#nome")
    public Permissao findByNome(String nome) {
        return this.permissaoRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma permissao com o nome '%s'.", nome)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<Permissao> findAll() {
        return this.permissaoRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nome)")
    public Set<Papel> findPapeis(String nome) {
        return this
            .findByNome(nome)
            .getPapeis()
            .stream()
            .map(PapelPermissao::getPapel)
            .collect(Collectors.toSet());
    }
}
