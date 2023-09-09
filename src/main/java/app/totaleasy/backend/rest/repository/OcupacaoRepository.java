package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Ocupacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OcupacaoRepository extends JpaRepository<Ocupacao, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<Ocupacao> findByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
