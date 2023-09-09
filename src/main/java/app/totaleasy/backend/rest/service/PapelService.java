package app.totaleasy.backend.rest.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.Papel;
import app.totaleasy.backend.rest.model.PapelPermissao;
import app.totaleasy.backend.rest.model.PapelUsuario;
import app.totaleasy.backend.rest.model.Permissao;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.repository.PapelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "papel")
public class PapelService {

    private final PapelRepository papelRepository;

    @Cacheable(key = "#nome")
    public Papel findByNome(String nome) {
        return this.papelRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> {
                throw new EntidadeNaoExisteException(String.format("Não foi encontrado nenhum papel de usuário com o nome '%s'.", nome));
            });
    }

    @Cacheable(key = "#root.methodName")
    public List<Papel> findAll() {
        return this.papelRepository.findAll();
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nome)")
    public Set<Usuario> findUsuarios(String nome) {
        return this
            .findByNome(nome)
            .getUsuarios()
            .stream()
            .map(PapelUsuario::getUsuario)
            .collect(Collectors.toSet());
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%s', #root.methodName, #nome)")
    public Set<Permissao> findPermissoes(String nome) {
        return this
            .findByNome(nome)
            .getPermissoes()
            .stream()
            .map(PapelPermissao::getPermissao)
            .collect(Collectors.toSet());
    }
}
