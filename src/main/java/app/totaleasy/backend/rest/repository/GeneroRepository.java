package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Genero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<Genero> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
