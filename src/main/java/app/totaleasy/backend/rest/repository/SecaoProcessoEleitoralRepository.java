package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.SecaoProcessoEleitoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SecaoProcessoEleitoralRepository extends JpaRepository<SecaoProcessoEleitoral, Integer> {

    boolean existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEProcessoEleitoral);

    Optional<SecaoProcessoEleitoral> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEProcessoEleitoral);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEProcessoEleitoral);

    @Modifying
    @Transactional
    void deleteByLocalVotacaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroTSELocalVotacao, Integer numeroTSEZona, String siglaUF);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF);

    @Modifying
    @Transactional
    void deleteByProcessoEleitoralCodigoTSE(Integer codigoTSEProcessoEleitoral);
}
