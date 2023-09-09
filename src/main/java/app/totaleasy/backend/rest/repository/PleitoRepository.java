package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Pleito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PleitoRepository extends JpaRepository<Pleito, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<Pleito> findByCodigoTSE(Integer codigoTSE);

    List<Pleito> findByProcessoEleitoralCodigoTSE(Integer codigoTSEProcessoEleitoral);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByProcessoEleitoralCodigoTSE(Integer codigoTSEProcessoEleitoral);
}
