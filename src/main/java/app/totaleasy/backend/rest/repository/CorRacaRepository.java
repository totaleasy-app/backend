package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.CorRaca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CorRacaRepository extends JpaRepository<CorRaca, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<CorRaca> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
