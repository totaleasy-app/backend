package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.Candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    boolean existsByCodigoTSEEqualsIgnoreCase(String codigoTSE);

    Optional<Candidato> findByCodigoTSEEqualsIgnoreCase(String codigoTSE);

    List<Candidato> findByGeneroCodigoTSE(Integer codigoTSE);

    List<Candidato> findByCorRacaCodigoTSE(Integer codigoTSE);

    List<Candidato> findByGrauInstrucaoCodigoTSE(Integer codigoTSE);

    List<Candidato> findByEstadoCivilCodigoTSE(Integer codigoTSE);

    List<Candidato> findByOcupacaoCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSEEqualsIgnoreCase(String codigoTSE);

    @Modifying
    @Transactional
    void deleteByGeneroCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCorRacaCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByGrauInstrucaoCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByEstadoCivilCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByOcupacaoCodigoTSE(Integer codigoTSE);
}
