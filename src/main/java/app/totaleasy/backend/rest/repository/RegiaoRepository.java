package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Regiao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {

    Optional<Regiao> findBySiglaEqualsIgnoreCase(String sigla);
}
