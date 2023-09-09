package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Papel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Integer> {

    Optional<Papel> findByNomeEqualsIgnoreCase(String nome);
}
