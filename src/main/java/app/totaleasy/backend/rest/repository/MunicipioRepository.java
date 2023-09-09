package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<Municipio> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
