package app.totaleasy.backend.rest.repository;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoRetrievalDTO;
import app.totaleasy.backend.rest.model.ApuracaoVotosPartidoBoletimUrna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApuracaoVotosPartidoBoletimUrnaRepository extends JpaRepository<ApuracaoVotosPartidoBoletimUrna, Integer> {

    boolean existsByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSEPartido, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    Optional<ApuracaoVotosPartidoBoletimUrna> findByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSEPartido, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByPartidoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSEPartido, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Query(
        value = """
            SELECT new app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosPartidoRetrievalDTO(
                pa.numeroTSE,
                pa.nome,
                cr.codigoTSE,
                cr.nome,
                e.codigoTSE,
                e.nome,
                pl.codigoTSE,
                pl.turno,
                SUM(avpbu.quantidadeVotosDeLegenda),
                SUM(avpbu.totalVotosApurados)
            )
            FROM
                ApuracaoVotosPartidoBoletimUrna AS avpbu INNER JOIN
                avpbu.partido AS pa INNER JOIN
                avpbu.cargoEleicao AS ce INNER JOIN
                ce.cargo AS cr INNER JOIN
                ce.eleicao AS e INNER JOIN
                e.pleito AS pl
            GROUP BY pa.numeroTSE, pa.nome, cr.codigoTSE, cr.nome, e.codigoTSE, e.nome, pl.codigoTSE, pl.turno
            ORDER BY SUM(avpbu.totalVotosApurados) DESC
        """
    )
    List<ApuracaoVotosPartidoRetrievalDTO> findApuracaoVotosPartidos();

    @Modifying
    @Transactional
    void deleteByBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByPartidoNumeroTSE(Integer numeroTSEPartido);

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao);
}
