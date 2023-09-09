package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.EstadoCivil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<EstadoCivil> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
