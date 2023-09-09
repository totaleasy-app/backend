package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.PapelPermissao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelPermissaoRepository extends JpaRepository<PapelPermissao, Integer> {

    Optional<PapelPermissao> findByPapelNomeEqualsIgnoreCaseAndPermissaoNomeEqualsIgnoreCase(String nomePapel, String nomePermissao);
}
