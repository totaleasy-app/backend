package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.model.CargoEleicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CargoEleicaoRepository extends JpaRepository<CargoEleicao, Integer> {

    boolean existsByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao);

    Optional<CargoEleicao> findByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao);

    List<CargoEleicao> findByCargoCodigoTSE(Integer codigoTSECargo);

    List<CargoEleicao> findByEleicaoCodigoTSE(Integer codigoTSEEleicao);

    @Modifying
    @Transactional
    void deleteByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao);

    @Modifying
    @Transactional
    void deleteByCargoCodigoTSE(Integer codigoTSECargo);

    @Modifying
    @Transactional
    void deleteByEleicaoCodigoTSE(Integer codigoTSEEleicao);
}
