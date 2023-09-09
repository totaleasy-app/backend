package app.totaleasy.backend.rest.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import app.totaleasy.backend.core.model.AgregacaoSecao;
import app.totaleasy.backend.core.model.ApuracaoVotosCandidatura;
import app.totaleasy.backend.core.model.ApuracaoVotosCargoEleicao;
import app.totaleasy.backend.core.model.ApuracaoVotosPartido;
import app.totaleasy.backend.core.model.Candidatura;
import app.totaleasy.backend.core.model.CandidaturaId;
import app.totaleasy.backend.core.model.CargoEleicao;
import app.totaleasy.backend.core.model.CargoEleicaoId;
import app.totaleasy.backend.core.model.CargoId;
import app.totaleasy.backend.core.model.EleicaoId;
import app.totaleasy.backend.core.model.EmissaoBoletimUrna;
import app.totaleasy.backend.core.model.PartidoId;
import app.totaleasy.backend.core.model.QRCodeBoletimUrna;
import app.totaleasy.backend.core.model.complemento.BoletimUrna;
import app.totaleasy.backend.core.model.complemento.Candidato;
import app.totaleasy.backend.core.model.complemento.CandidatoId;
import app.totaleasy.backend.core.model.complemento.Cargo;
import app.totaleasy.backend.core.model.complemento.Eleicao;
import app.totaleasy.backend.core.model.complemento.Partido;
import app.totaleasy.backend.core.service.BoletimUrnaBuilder;
import app.totaleasy.backend.rest.dto.build.BoletimUrnaBuildDTO;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaIdDTO;
import app.totaleasy.backend.rest.dto.id.BoletimUrnaUsuarioIdDTO;
import app.totaleasy.backend.rest.exception.EntidadeJaExisteException;
import app.totaleasy.backend.rest.exception.EntidadeNaoExisteException;
import app.totaleasy.backend.rest.model.ApuracaoVotosCandidaturaBoletimUrna;
import app.totaleasy.backend.rest.model.ApuracaoVotosCargoBoletimUrna;
import app.totaleasy.backend.rest.model.ApuracaoVotosPartidoBoletimUrna;
import app.totaleasy.backend.rest.model.BoletimUrnaUsuario;
import app.totaleasy.backend.rest.model.Fase;
import app.totaleasy.backend.rest.model.LocalVotacao;
import app.totaleasy.backend.rest.model.Municipio;
import app.totaleasy.backend.rest.model.OrigemBoletimUrna;
import app.totaleasy.backend.rest.model.OrigemConfiguracaoProcessoEleitoral;
import app.totaleasy.backend.rest.model.Pleito;
import app.totaleasy.backend.rest.model.ProcessoEleitoral;
import app.totaleasy.backend.rest.model.Secao;
import app.totaleasy.backend.rest.model.SecaoPleito;
import app.totaleasy.backend.rest.model.SecaoProcessoEleitoral;
import app.totaleasy.backend.rest.model.TipoCargo;
import app.totaleasy.backend.rest.model.UF;
import app.totaleasy.backend.rest.model.UrnaEletronica;
import app.totaleasy.backend.rest.model.Usuario;
import app.totaleasy.backend.rest.model.Zona;
import app.totaleasy.backend.rest.repository.BoletimUrnaUsuarioRepository;
import app.totaleasy.backend.rest.util.DataTypeConverter;

@Service
@CacheConfig(cacheNames = "boletim-urna-usuario")
public class BoletimUrnaUsuarioService {

    private final UFService ufService;

    private final UrnaEletronicaService urnaEletronicaService;

    private final MunicipioService municipioService;

    private final ZonaService zonaService;

    private final SecaoService secaoService;

    private final LocalVotacaoService localVotacaoService;

    private final OrigemConfiguracaoProcessoEleitoralService origemConfiguracaoProcessoEleitoralService;

    private final ProcessoEleitoralService processoEleitoralService;

    private final PleitoService pleitoService;

    private final EleicaoService eleicaoService;

    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    private final SecaoPleitoService secaoPleitoService;

    private final TipoCargoService tipoCargoService;

    private final CargoService cargoService;

    private final CargoEleicaoService cargoEleicaoService;

    private final PartidoService partidoService;

    private final CandidatoService candidatoService;

    private final CandidaturaService candidaturaService;

    private final FaseService faseService;

    private final OrigemBoletimUrnaService origemBoletimUrnaService;

    private final QRCodeBoletimUrnaService qrCodeBoletimUrnaService;

    private final ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService;

    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;

    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;

    private final BoletimUrnaService boletimUrnaService;

    private final BoletimUrnaUsuarioRepository boletimUrnaUsuarioRepository;

    private final AgregacaoSecaoService agregacaoSecaoService;

    private final CachingService cachingService;

    @Autowired
    public BoletimUrnaUsuarioService(
        UFService ufService,
        UrnaEletronicaService urnaEletronicaService,
        MunicipioService municipioService,
        ZonaService zonaService,
        SecaoService secaoService,
        LocalVotacaoService localVotacaoService,
        OrigemConfiguracaoProcessoEleitoralService origemConfiguracaoProcessoEleitoralService,
        ProcessoEleitoralService processoEleitoralService,
        PleitoService pleitoService,
        EleicaoService eleicaoService,
        SecaoProcessoEleitoralService secaoProcessoEleitoralService,
        @Lazy SecaoPleitoService secaoPleitoService,
        TipoCargoService tipoCargoService,
        CargoService cargoService,
        CargoEleicaoService cargoEleicaoService,
        PartidoService partidoService,
        CandidatoService candidatoService,
        CandidaturaService candidaturaService,
        FaseService faseService,
        OrigemBoletimUrnaService origemBoletimUrnaService,
        QRCodeBoletimUrnaService qrCodeBoletimUrnaService,
        ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService,
        ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService,
        ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService,
        BoletimUrnaService boletimUrnaService,
        BoletimUrnaUsuarioRepository boletimUrnaUsuarioRepository,
        AgregacaoSecaoService agregacaoSecaoService,
        CachingService cachingService
    ) {
        this.ufService = ufService;
        this.urnaEletronicaService = urnaEletronicaService;
        this.municipioService = municipioService;
        this.zonaService = zonaService;
        this.secaoService = secaoService;
        this.localVotacaoService = localVotacaoService;
        this.origemConfiguracaoProcessoEleitoralService = origemConfiguracaoProcessoEleitoralService;
        this.processoEleitoralService = processoEleitoralService;
        this.pleitoService = pleitoService;
        this.eleicaoService = eleicaoService;
        this.secaoProcessoEleitoralService = secaoProcessoEleitoralService;
        this.secaoPleitoService = secaoPleitoService;
        this.tipoCargoService = tipoCargoService;
        this.cargoService = cargoService;
        this.cargoEleicaoService = cargoEleicaoService;
        this.partidoService = partidoService;
        this.candidatoService = candidatoService;
        this.candidaturaService = candidaturaService;
        this.faseService = faseService;
        this.origemBoletimUrnaService = origemBoletimUrnaService;
        this.qrCodeBoletimUrnaService = qrCodeBoletimUrnaService;
        this.apuracaoVotosCandidaturaBoletimUrnaService = apuracaoVotosCandidaturaBoletimUrnaService;
        this.apuracaoVotosCargoBoletimUrnaService = apuracaoVotosCargoBoletimUrnaService;
        this.apuracaoVotosPartidoBoletimUrnaService = apuracaoVotosPartidoBoletimUrnaService;
        this.boletimUrnaService = boletimUrnaService;
        this.boletimUrnaUsuarioRepository = boletimUrnaUsuarioRepository;
        this.agregacaoSecaoService = agregacaoSecaoService;
        this.cachingService = cachingService;
    }

    @Cacheable(key = "T(java.lang.String).format('%s:%d:%d:%s:%d', #id.username, #id.numeroTSESecao, #id.numeroTSEZona, #id.siglaUF, #id.codigoTSEPleito)")
    public BoletimUrnaUsuario findById(BoletimUrnaUsuarioIdDTO id) {
        return this.boletimUrnaUsuarioRepository
            .findByUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
                id.getUsername(),
                id.getNumeroTSESecao(),
                id.getNumeroTSEZona(),
                id.getSiglaUF(),
                id.getCodigoTSEPleito()
            )
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma relação entre boletim de urna e usuário identificada por %s.", id)
            ));
    }

    @Cacheable(key = "#root.methodName")
    public List<BoletimUrnaUsuario> findAll() {
        return this.boletimUrnaUsuarioRepository.findAll();
    }

    public BoletimUrnaUsuario saveIfNotExists(BoletimUrnaUsuario boletimUrnaUsuario) {
        if (this.boletimUrnaUsuarioRepository.existsByUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            boletimUrnaUsuario.getUsuario().getUsername(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE()
        )) {
            throw new EntidadeJaExisteException(
                String.format(
                    "O usuário '%s' já coletou um boletim de urna no %s na UF '%s', zona %d, seção %d.",
                    boletimUrnaUsuario.getUsuario().getUsername(),
                    boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getPleito().getNome(),
                    boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla(),
                    boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
                    boletimUrnaUsuario.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE()
                )
            );
        }

        this.cachingService.evictAllCaches();

        return this.boletimUrnaUsuarioRepository.save(boletimUrnaUsuario);
    }

    public void deleteById(BoletimUrnaUsuarioIdDTO id) {
        id.validate();

        if (!this.boletimUrnaUsuarioRepository.existsByUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            id.getUsername(),
            id.getNumeroTSESecao(),
            id.getNumeroTSEZona(),
            id.getSiglaUF(),
            id.getCodigoTSEPleito()
        )) {
            throw new EntidadeNaoExisteException(String.format("Não foi encontrada nenhuma relação entre boletim de urna e usuário identificada por %s.", id));
        }

        this.boletimUrnaUsuarioRepository.deleteByUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            id.getUsername(),
            id.getNumeroTSESecao(),
            id.getNumeroTSEZona(),
            id.getSiglaUF(),
            id.getCodigoTSEPleito()
        );

        this.cachingService.evictAllCaches();
    }

    public void deleteByBoletimUrna(BoletimUrnaIdDTO id) {
        id.validate();

        this.boletimUrnaUsuarioRepository.deleteByBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSE(
            id.getNumeroTSESecao(),
            id.getNumeroTSEZona(),
            id.getSiglaUF(),
            id.getCodigoTSEPleito()
        );

        this.cachingService.evictAllCaches();
    }

    public void deleteByUsuario(String username) {
        this.boletimUrnaUsuarioRepository.deleteByUsuarioUsernameEqualsIgnoreCase(username);

        this.cachingService.evictAllCaches();
    }

    public void build(BoletimUrnaBuildDTO boletimUrnaBuildDTO, Usuario usuario) {
        boletimUrnaBuildDTO.validate();

        app.totaleasy.backend.core.model.BoletimUrna boletimUrnaQRCode = BoletimUrnaBuilder.build(boletimUrnaBuildDTO.getPayloads());

        BoletimUrna complementoBoletimUrna = boletimUrnaQRCode.getDadosComplementares();

        UF uf = this.ufService.findBySigla(boletimUrnaQRCode.getConteudo().getUF().getSigla());

        UrnaEletronica urnaEletronica = this.urnaEletronicaService.getIfExistsOrElseSave(new UrnaEletronica(
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getNumeroSerie(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getCodigoIdentificacaoCarga(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getVersaoSoftware(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getDataAbertura(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getHorarioAbertura(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getDataFechamento(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getHorarioFechamento()
        ));

        Municipio municipio = this.municipioService.getIfExistsOrElseSave(new Municipio(
            complementoBoletimUrna.getProcessoEleitoral().getMunicipio().getCodigoTSE(),
            complementoBoletimUrna.getProcessoEleitoral().getMunicipio().getNome(),
            uf
        ));

        Zona zona = this.zonaService.getIfExistsOrElseSave(new Zona(
            boletimUrnaQRCode.getConteudo().getZona().getNumeroTSE(),
            uf
        ));

        Secao secao = this.secaoService.getIfExistsOrElseSave(new Secao(
            boletimUrnaQRCode.getConteudo().getSecao().getNumeroTSE(),
            zona
        ));

        LocalVotacao localVotacao = this.localVotacaoService.getIfExistsOrElseSave(new LocalVotacao(
            boletimUrnaQRCode.getConteudo().getLocalVotacao().getNumeroTSE(),
            zona,
            municipio
        ));

        OrigemConfiguracaoProcessoEleitoral origemConfiguracaoProcessoEleitoral = this.origemConfiguracaoProcessoEleitoralService.getIfExistsOrElseSave(new OrigemConfiguracaoProcessoEleitoral(
            boletimUrnaQRCode.getConteudo().getProcessoEleitoral().getOrigemConfiguracao().getNome(),
            boletimUrnaQRCode.getConteudo().getProcessoEleitoral().getOrigemConfiguracao().getNomeAbreviado()
        ));

        ProcessoEleitoral processoEleitoral = this.processoEleitoralService.getIfExistsOrElseSave(new ProcessoEleitoral(
            complementoBoletimUrna.getProcessoEleitoral().getCodigoTSE(),
            complementoBoletimUrna.getProcessoEleitoral().getNome(),
            origemConfiguracaoProcessoEleitoral
        ));

        Pleito pleito = this.pleitoService.getIfExistsOrElseSave(new Pleito(
            complementoBoletimUrna.getProcessoEleitoral().getPleito().getCodigoTSE(),
            complementoBoletimUrna.getProcessoEleitoral().getPleito().getNome().toLowerCase(),
            boletimUrnaQRCode.getConteudo().getPleito().getTurno().getNumero(),
            DataTypeConverter.toLocalDate(complementoBoletimUrna.getProcessoEleitoral().getPleito().getData(), "dd/MM/yyyy"),
            processoEleitoral
        ));

        SecaoPleito secaoPleito = this.secaoPleitoService.getIfExistsOrElseSave(new SecaoPleito(
            secao,
            pleito,
            boletimUrnaQRCode.getConteudo().getSecaoPleito().getQuantidadeEleitoresAptos(),
            boletimUrnaQRCode.getConteudo().getSecaoPleito().getQuantidadeEleitoresComparecentes(),
            boletimUrnaQRCode.getConteudo().getSecaoPleito().getQuantidadeEleitoresFaltosos(),
            boletimUrnaQRCode.getConteudo().getSecaoPleito().getQuantidadeEleitoresHabilitadosPorAnoNascimento()
        ));

        for (AgregacaoSecao agregacaoSecao : boletimUrnaQRCode.getConteudo().getSecoesAgregadas()) {
            Secao secaoAgregada = this.secaoService.getIfExistsOrElseSave(new Secao(
                agregacaoSecao.getSecaoAgregada().getNumeroTSE(),
                zona
            ));

            this.agregacaoSecaoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.AgregacaoSecao(
                secao,
                secaoAgregada,
                processoEleitoral
            ));
        }

        Map<EleicaoId, app.totaleasy.backend.rest.model.Eleicao> eleicoes = new HashMap<>();

        for (Eleicao complementoEleicao : complementoBoletimUrna.getProcessoEleitoral().getEleicoes().values()) {
            app.totaleasy.backend.core.model.Eleicao eleicao = boletimUrnaQRCode
                .getConteudo()
                .getEleicoes()
                .get(new EleicaoId(complementoEleicao.getCodigoTSE()));

            eleicoes.put(
                new EleicaoId(complementoEleicao.getCodigoTSE()),
                this.eleicaoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.Eleicao(
                    complementoEleicao.getCodigoTSE(),
                    complementoEleicao.getNome(),
                    pleito.getAno(),
                    eleicao == null ? null : eleicao.getQuantidadeVotosCargosMajoritarios(),
                    eleicao == null ? null : eleicao.getQuantidadeVotosCargosProporcionais(),
                    pleito
                ))
            );
        }

        this.secaoProcessoEleitoralService.getIfExistsOrElseSave(new SecaoProcessoEleitoral(
            secao,
            processoEleitoral,
            localVotacao
        ));

        Map<Integer, TipoCargo> tiposCargo = new HashMap<>();

        for (app.totaleasy.backend.core.model.Cargo cargo : boletimUrnaQRCode.getConteudo().getCargos().values()) {
            tiposCargo.put(
                cargo.getTipo().getCodigoTSE(),
                this.tipoCargoService.getIfExistsOrElseSave(new TipoCargo(
                    cargo.getTipo().getCodigoTSE(),
                    cargo.getTipo().getNome()
                ))
            );
        }

        Map<CargoId, app.totaleasy.backend.rest.model.Cargo> cargos = new HashMap<>();

        for (app.totaleasy.backend.core.model.Cargo cargo : boletimUrnaQRCode.getConteudo().getCargos().values()) {
            Cargo complementoCargo = complementoBoletimUrna
                .getProcessoEleitoral()
                .getCargos()
                .get(new CargoId(cargo.getCodigoTSE()));

            cargos.put(
                new CargoId(cargo.getCodigoTSE()),
                this.cargoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.Cargo(
                    cargo.getCodigoTSE(),
                    complementoCargo.getNomeNeutro(),
                    complementoCargo.getNomeAbreviado(),
                    tiposCargo.get(cargo.getTipo().getCodigoTSE())
                ))
            );
        }

        Map<CargoEleicaoId, app.totaleasy.backend.rest.model.CargoEleicao> cargosEleicoes = new HashMap<>();

        for (CargoEleicao cargoEleicao : boletimUrnaQRCode.getConteudo().getCargosEleicoes().values()) {
            cargosEleicoes.put(
                new CargoEleicaoId(cargoEleicao.getCargo().getCodigoTSE(), cargoEleicao.getEleicao().getCodigoTSE()),
                this.cargoEleicaoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.CargoEleicao(
                    cargos.get(new CargoId(cargoEleicao.getCargo().getCodigoTSE())),
                    eleicoes.get(new EleicaoId(cargoEleicao.getEleicao().getCodigoTSE()))
                ))
            );
        }

        Map<PartidoId, app.totaleasy.backend.rest.model.Partido> partidos = new HashMap<>();

        for (app.totaleasy.backend.core.model.Partido partido : boletimUrnaQRCode.getConteudo().getPartidos().values()) {
            Partido complementoPartido = complementoBoletimUrna
                .getProcessoEleitoral()
                .getPartidos()
                .get(new PartidoId(partido.getNumeroTSE()));

            partidos.put(
                new PartidoId(partido.getNumeroTSE()),
                this.partidoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.Partido(
                    partido.getNumeroTSE(),
                    complementoPartido == null ? null : complementoPartido.getNome(),
                    complementoPartido == null ? null : complementoPartido.getSigla()
                ))
            );
        }

        Map<CandidatoId, app.totaleasy.backend.rest.model.Candidato> candidatos = new LinkedHashMap<>();

        for (Candidatura candidatura : boletimUrnaQRCode.getConteudo().getCandidaturas().values()) {
            Candidato complementoCandidato = complementoBoletimUrna
                .getProcessoEleitoral()
                .getCandidatosTitulares().get(
                    new CandidatoId(
                        candidatura.getNumeroTSECandidato(),
                        candidatura.getCargoEleicao().getCargo().getCodigoTSE()
                    )
                );

            candidatos.put(
                new CandidatoId(
                    candidatura.getNumeroTSECandidato(),
                    candidatura.getCargoEleicao().getCargo().getCodigoTSE()
                ),
                this.candidatoService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.Candidato(
                    candidatura.getNumeroTSECandidato(),
                    complementoCandidato.getCodigoTSE(),
                    complementoCandidato.getNome()
                ))
            );
        }

        Map<CandidaturaId, app.totaleasy.backend.rest.model.Candidatura> candidaturas = new HashMap<>();

        for (Candidatura candidatura : boletimUrnaQRCode.getConteudo().getCandidaturas().values()) {
            candidaturas.put(
                new CandidaturaId(
                    candidatura.getNumeroTSECandidato(),
                    candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
                    candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
                ),
                this.candidaturaService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.Candidatura(
                    candidatos.get(new CandidatoId(
                        candidatura.getNumeroTSECandidato(),
                        candidatura.getCargoEleicao().getCargo().getCodigoTSE()
                    )),
                    cargosEleicoes.get(new CargoEleicaoId(
                        candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
                        candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
                    )),
                    partidos.get(new PartidoId(candidatura.getPartido().getNumeroTSE()))
                ))
            );
        }

        Fase fase = this.faseService.getIfExistsOrElseSave(new Fase(
            boletimUrnaQRCode.getConteudo().getFase().getCodigoTSE(),
            boletimUrnaQRCode.getConteudo().getFase().getNome()
        ));

        OrigemBoletimUrna origemBoletimUrna = this.origemBoletimUrnaService.getIfExistsOrElseSave(new OrigemBoletimUrna(
            boletimUrnaQRCode.getConteudo().getOrigem().getNome(),
            boletimUrnaQRCode.getConteudo().getOrigem().getNomeAbreviado()
        ));

        EmissaoBoletimUrna emissaoBoletimUrna = boletimUrnaQRCode.getConteudo().getEmissaoBoletimUrna();

        app.totaleasy.backend.rest.model.BoletimUrna boletimUrna = boletimUrnaService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.BoletimUrna(
            secaoPleito,
            fase,
            origemBoletimUrna,
            urnaEletronica,
            boletimUrnaQRCode.getAssinatura(),
            boletimUrnaQRCode.getQuantidadeTotalQRCodes(),
            emissaoBoletimUrna == null ? null : emissaoBoletimUrna.getData(),
            emissaoBoletimUrna == null ? null : emissaoBoletimUrna.getHorario()
        ));

        BoletimUrnaUsuario boletimUrnaUsuario = this.saveIfNotExists(new BoletimUrnaUsuario(
            boletimUrna,
            usuario
        ));

        for (QRCodeBoletimUrna qrCodeBoletimUrna : boletimUrnaQRCode.getQRCodes()) {
            this.qrCodeBoletimUrnaService.getIfExistsOrElseSave(new app.totaleasy.backend.rest.model.QRCodeBoletimUrna(
                boletimUrna,
                qrCodeBoletimUrna.getPayloadCabecalho(),
                qrCodeBoletimUrna.getPayloadConteudo(),
                qrCodeBoletimUrna.getHash(),
                qrCodeBoletimUrna.getCabecalho().getMarcaInicioDados().getIndiceQRCode(),
                qrCodeBoletimUrna.getCabecalho().getVersaoFormatoRepresentacao().getNumeroCiclosEleitoraisDesdeImplementacao(),
                qrCodeBoletimUrna.getCabecalho().getVersaoFormatoRepresentacao().getNumeroRevisoesFormatoCiclo(),
                qrCodeBoletimUrna.getCabecalho().getVersaoChaveAssinatura()
            ));
        }

        for (ApuracaoVotosCandidatura apuracaoVotosCandidatura : boletimUrnaQRCode.getConteudo().getApuracoesVotosCandidaturas().values()) {
            this.apuracaoVotosCandidaturaBoletimUrnaService.getIfExistsOrElseSave(new ApuracaoVotosCandidaturaBoletimUrna(
                candidaturas.get(new CandidaturaId(
                    apuracaoVotosCandidatura.getCandidatura().getNumeroTSECandidato(),
                    apuracaoVotosCandidatura.getCandidatura().getCargoEleicao().getCargo().getCodigoTSE(),
                    apuracaoVotosCandidatura.getCandidatura().getCargoEleicao().getEleicao().getCodigoTSE()
                )),
                boletimUrna,
                apuracaoVotosCandidatura.getTotalVotosApurados()
            ));
        }

        for (ApuracaoVotosCargoEleicao apuracaoVotosCargoEleicao : boletimUrnaQRCode.getConteudo().getApuracoesVotosCargos().values()) {
            this.apuracaoVotosCargoBoletimUrnaService.getIfExistsOrElseSave(new ApuracaoVotosCargoBoletimUrna(
                cargosEleicoes.get(new CargoEleicaoId(
                    apuracaoVotosCargoEleicao.getCargoEleicao().getCargo().getCodigoTSE(),
                    apuracaoVotosCargoEleicao.getCargoEleicao().getEleicao().getCodigoTSE()
                )),
                boletimUrna,
                apuracaoVotosCargoEleicao.getQuantidadeEleitoresAptos(),
                apuracaoVotosCargoEleicao.getQuantidadeComparecimentoCargoSemCandidatos(),
                apuracaoVotosCargoEleicao.getQuantidadeVotosNominais(),
                apuracaoVotosCargoEleicao.getQuantidadeVotosDeLegenda(),
                apuracaoVotosCargoEleicao.getQuantidadeVotosEmBranco(),
                apuracaoVotosCargoEleicao.getQuantidadeVotosNulos(),
                apuracaoVotosCargoEleicao.getTotalVotosApurados()
            ));
        }

        for (ApuracaoVotosPartido apuracaoVotosPartido : boletimUrnaQRCode.getConteudo().getApuracoesVotosPartidos().values()) {
            this.apuracaoVotosPartidoBoletimUrnaService.getIfExistsOrElseSave(new ApuracaoVotosPartidoBoletimUrna(
                partidos.get(new PartidoId(apuracaoVotosPartido.getPartido().getNumeroTSE())),
                boletimUrna,
                cargosEleicoes.get(new CargoEleicaoId(
                    apuracaoVotosPartido.getCargoEleicao().getCargo().getCodigoTSE(),
                    apuracaoVotosPartido.getCargoEleicao().getEleicao().getCodigoTSE()
                )),
                apuracaoVotosPartido.getQuantidadeVotosDeLegenda(),
                apuracaoVotosPartido.getTotalVotosApurados()
            ));
        }
    }
}
