package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.GrauInstrucao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GrauInstrucaoRepository extends JpaRepository<GrauInstrucao, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<GrauInstrucao> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
