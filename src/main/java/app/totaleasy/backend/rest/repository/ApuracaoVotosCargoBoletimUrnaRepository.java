package app.totaleasy.backend.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO;
import app.totaleasy.backend.rest.model.ApuracaoVotosCargoBoletimUrna;

public interface ApuracaoVotosCargoBoletimUrnaRepository extends JpaRepository<ApuracaoVotosCargoBoletimUrna, Integer> {

    boolean existsByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    Optional<ApuracaoVotosCargoBoletimUrna> findByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Query(
        value = """
            SELECT new app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO(
                cr.codigoTSE,
                cr.nome,
                e.codigoTSE,
                e.nome,
                p.codigoTSE,
                p.turno,
                SUM(avcbu.quantidadeVotosNominais),
                SUM(avcbu.quantidadeVotosDeLegenda),
                SUM(avcbu.quantidadeVotosEmBranco),
                SUM(avcbu.quantidadeVotosNulos),
                SUM(avcbu.totalVotosApurados)
            )
            FROM
                ApuracaoVotosCargoBoletimUrna AS avcbu INNER JOIN
                avcbu.cargoEleicao AS ce INNER JOIN
                ce.cargo AS cr INNER JOIN
                ce.eleicao AS e INNER JOIN
                e.pleito AS p
            GROUP BY cr.codigoTSE, cr.nome, e.codigoTSE, e.nome, p.codigoTSE, p.turno
            ORDER BY SUM(avcbu.totalVotosApurados) DESC
        """
    )
    List<ApuracaoVotosCargoRetrievalDTO> findApuracoesVotosCargos();

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer codigoTSECargo, Integer codigoTSEEleicao);
}
