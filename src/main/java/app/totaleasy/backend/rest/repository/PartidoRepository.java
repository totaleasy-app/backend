package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Partido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    Optional<Partido> findByNumeroTSE(Integer numeroTSE);

    boolean existsByNumeroTSE(Integer numeroTSE);

    @Modifying
    @Transactional
    void deleteByNumeroTSE(Integer numeroTSE);
}
