package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.SecaoPleito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SecaoPleitoRepository extends JpaRepository<SecaoPleito, Integer> {

    boolean existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    Optional<SecaoPleito> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    List<SecaoPleito> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF);

    List<SecaoPleito> findByPleitoCodigoTSE(Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF);

    @Modifying
    @Transactional
    void deleteByPleitoCodigoTSE(Integer codigoTSEPleito);
}
