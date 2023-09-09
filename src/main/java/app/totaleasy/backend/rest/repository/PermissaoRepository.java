package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Permissao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {

    Optional<Permissao> findByNomeEqualsIgnoreCase(String nome);
}
