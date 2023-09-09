package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.ProcessoEleitoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProcessoEleitoralRepository extends JpaRepository<ProcessoEleitoral, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<ProcessoEleitoral> findByCodigoTSE(Integer codigoTSE);

    List<ProcessoEleitoral> findByOrigemConfiguracaoNomeAbreviadoEqualsIgnoreCase(String nomeAbreviadoOrigemConfiguracao);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);

    void deleteByOrigemConfiguracaoNomeAbreviadoEqualsIgnoreCase(String nomeAbreviadoOrigemConfiguracao);
}
