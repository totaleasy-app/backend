package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.OrigemBoletimUrna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrigemBoletimUrnaRepository extends JpaRepository<OrigemBoletimUrna, Integer> {

    boolean existsByNomeAbreviadoEqualsIgnoreCase(String nomeAbreviado);

    Optional<OrigemBoletimUrna> findByNomeAbreviadoEqualsIgnoreCase(String nomeAbreviado);

    void deleteByNomeAbreviadoEqualsIgnoreCase(String nomeAbreviado);
}
