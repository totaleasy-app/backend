package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.UF;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Integer> {

    Optional<UF> findBySiglaEqualsIgnoreCase(String sigla);
}
