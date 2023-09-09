package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Zona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {

    boolean existsByNumeroTSEAndUfSiglaEqualsIgnoreCase(Integer numeroTSE, String siglaUF);

    Optional<Zona> findByNumeroTSEAndUfSiglaEqualsIgnoreCase(Integer numeroTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByNumeroTSEAndUfSiglaEqualsIgnoreCase(Integer numeroTSE, String siglaUF);
}
