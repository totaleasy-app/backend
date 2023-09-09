package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.TipoCargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TipoCargoRepository extends JpaRepository<TipoCargo, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<TipoCargo> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
