package app.totaleasy.backend.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import app.totaleasy.backend.rest.model.ApuracaoVotosCandidaturaBoletimUrna;

public interface ApuracaoVotosCandidaturaBoletimUrnaRepository extends JpaRepository<ApuracaoVotosCandidaturaBoletimUrna, Integer> {

    boolean existsByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSECandidato, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    Optional<ApuracaoVotosCandidaturaBoletimUrna> findByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSECandidato, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Query(
        value = """
            SELECT new app.totaleasy.backend.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO(
                ca.numeroTSE,
                ca.nomeUrna,
                cr.codigoTSE,
                cr.nome,
                e.codigoTSE,
                e.nome,
                p.codigoTSE,
                p.turno,
                SUM(avcbu.totalVotosApurados)
            )
            FROM
                ApuracaoVotosCandidaturaBoletimUrna AS avcbu INNER JOIN
                avcbu.candidatura AS cd INNER JOIN
                cd.candidato AS ca INNER JOIN
                cd.cargoEleicao AS ce INNER JOIN
                ce.cargo AS cr INNER JOIN
                ce.eleicao AS e INNER JOIN
                e.pleito AS p
            GROUP BY ca.numeroTSE, ca.nomeUrna, cr.codigoTSE, cr.nome, e.codigoTSE, e.nome, p.codigoTSE, p.turno
            ORDER BY SUM(avcbu.totalVotosApurados) DESC
        """
    )
    List<ApuracaoVotosCandidaturaRetrievalDTO> findApuracaoVotosCandidaturas();

    @Modifying
    @Transactional
    void deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSECandidato, Integer codigoTSECargo, Integer codigoTSEEleicao, Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(Integer numeroTSESecao, Integer numeroTSEZona, String siglaUF, Integer codigoTSEPleito);

    @Modifying
    @Transactional
    void deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSE(Integer numeroTSECandidato, Integer codigoTSECargo, Integer codigoTSEEleicao);
}
