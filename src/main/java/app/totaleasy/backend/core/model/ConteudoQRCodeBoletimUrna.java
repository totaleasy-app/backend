package app.totaleasy.backend.core.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import app.totaleasy.backend.core.util.DataTypeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class ConteudoQRCodeBoletimUrna {

    private OrigemBoletimUrna origem;

    private ProcessoEleitoral processoEleitoral;

    private Pleito pleito;

    private Fase fase;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private UF uf;

    private Municipio municipio;

    private Zona zona;

    private Secao secao;

    private SecaoProcessoEleitoral secaoProcessoEleitoral;

    private SecaoPleito secaoPleito;

    private LocalVotacao localVotacao;

    private UrnaEletronica urnaEletronica;

    private ResponsavelApuracaoVotos responsavelApuracaoVotos;

    private EmissaoBoletimUrna emissaoBoletimUrna;

    private Map<CargoId, Cargo> cargos;

    private Map<EleicaoId, Eleicao> eleicoes;

    private Map<CargoEleicaoId, CargoEleicao> cargosEleicoes;

    private Map<PartidoId, Partido> partidos;

    private Map<CandidaturaId, Candidatura> candidaturas;

    private Map<ApuracaoVotosCandidaturaId, ApuracaoVotosCandidatura> apuracoesVotosCandidaturas;

    private Map<ApuracaoVotosCargoEleicaoId, ApuracaoVotosCargoEleicao> apuracoesVotosCargos;

    private Map<ApuracaoVotosPartidoId, ApuracaoVotosPartido> apuracoesVotosPartidos;

    private List<AgregacaoSecao> secoesAgregadas;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Cargo ultimoCargo;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Eleicao ultimaEleicao;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private Partido ultimoPartido;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String FORMATO_DATA = "yyyyMMdd";

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String FORMATO_HORARIO = "HHmmss";

    private static final Set<Integer> CODIGOS_TSE_CARGOS_COM_APENAS_UM_CANDIDATO_POR_PARTIDO = Set.of(1, 3, 5, 11);

    public ConteudoQRCodeBoletimUrna() {
        this.processoEleitoral = new ProcessoEleitoral();
        this.pleito = new Pleito();
        this.municipio = new Municipio();
        this.zona = new Zona();
        this.secao = new Secao();
        this.secaoProcessoEleitoral = new SecaoProcessoEleitoral();
        this.secaoPleito = new SecaoPleito();
        this.localVotacao = new LocalVotacao();
        this.urnaEletronica = new UrnaEletronica();
        this.responsavelApuracaoVotos = new ResponsavelApuracaoVotos();
        this.emissaoBoletimUrna = new EmissaoBoletimUrna();
        this.cargos = new LinkedHashMap<>();
        this.eleicoes = new LinkedHashMap<>();
        this.cargosEleicoes = new LinkedHashMap<>();
        this.partidos = new LinkedHashMap<>();
        this.candidaturas = new LinkedHashMap<>();
        this.apuracoesVotosCandidaturas = new LinkedHashMap<>();
        this.apuracoesVotosCargos = new LinkedHashMap<>();
        this.apuracoesVotosPartidos = new LinkedHashMap<>();
        this.secoesAgregadas = new LinkedList<>();
    }

    public UF getUF() {
        return this.uf;
    }

    public void setUF(UF uf) {
        this.uf = uf;
    }

    public void setOrigem(String string) {
        this.origem = OrigemBoletimUrna.fromNomeAbreviado(string).orElse(null);
    }

    public void setOrigemConfiguracaoProcessoEleitoral(String nomeAbreviadoOrigemConfiguracaoProcessoEleitoral) {
        this.processoEleitoral.setOrigemConfiguracao(
            OrigemConfiguracaoProcessoEleitoral.fromNomeAbreviado(
                nomeAbreviadoOrigemConfiguracaoProcessoEleitoral
            ).orElse(null));
    }

    public void setCodigoTSEProcessoEleitoral(String codigoTSEProcessoEleitoralString) {
        this.processoEleitoral.setCodigoTSE(DataTypeConverter.toInteger(codigoTSEProcessoEleitoralString));
    }

    public void setDataPleito(String dataString) {
        this.pleito.setData(DataTypeConverter.toLocalDate(dataString, FORMATO_DATA));
    }

    public void setCodigoTSEPleito(String codigoTSEPleitoString) {
        this.pleito.setCodigoTSE(DataTypeConverter.toInteger(codigoTSEPleitoString));
    }

    public void setTurnoPleito(String turnoString) {
        this.pleito.setTurno(Turno.fromNumero(DataTypeConverter.toInteger(turnoString)).orElse(null));
    }

    public void setFase(String nomeAbreviadoFase) {
        this.fase = Fase.fromNomeAbreviado(nomeAbreviadoFase).orElse(null);
    }

    public void setUF(String siglaUF) {
        this.uf = UF.fromSigla(siglaUF).orElse(null);
    }

    public void setCodigoTSEMunicipio(String codigoTSEMunicipioString) {
        this.municipio.setCodigoTSE(DataTypeConverter.toInteger(codigoTSEMunicipioString));
    }

    public void setNumeroTSEZona(String numeroTSEZonaString) {
        this.zona.setNumeroTSE(DataTypeConverter.toInteger(numeroTSEZonaString));
    }

    public void setNumeroTSESecao(String numeroTSESecaoString) {
        this.secao.setNumeroTSE(DataTypeConverter.toInteger(numeroTSESecaoString));
    }

    public void setSecoesAgregadas(String secoesAgregadas) {
        for (String numeroTSESecaoAgregada : secoesAgregadas.split("\\.")) {
            this.secoesAgregadas.add(new AgregacaoSecao(
                this.secao,
                new Secao(
                    DataTypeConverter.toInteger(numeroTSESecaoAgregada),
                    this.zona,
                    this.municipio
                ),
                this.processoEleitoral
            ));
        }
    }

    public void setQuantidadeEleitoresAptosSecaoPleito(String quantidadeEleitoresAptosString) {
        this.secaoPleito.setQuantidadeEleitoresAptos(DataTypeConverter.toInteger(quantidadeEleitoresAptosString));
    }

    public void setQuantidadeEleitoresComparecentesSecaoPleito(String quantidadeEleitoresComparecentesString) {
        this.secaoPleito.setQuantidadeEleitoresComparecentes(
            DataTypeConverter.toInteger(quantidadeEleitoresComparecentesString)
        );
    }

    public void setQuantidadeEleitoresFaltososSecaoPleito(String quantidadeEleitoresFaltososString) {
        this.secaoPleito.setQuantidadeEleitoresFaltosos(DataTypeConverter.toInteger(quantidadeEleitoresFaltososString));
    }

    public void setQuantidadeEleitoresHabilitadosPorAnoNascimentoSecaoPleito(
        String quantidadeEleitoresHabilitadosPorAnoNascimentoString
    ) {
        this.secaoPleito.setQuantidadeEleitoresHabilitadosPorAnoNascimento(
            DataTypeConverter.toInteger(quantidadeEleitoresHabilitadosPorAnoNascimentoString)
        );
    }

    public void setNumeroSerieUrnaEletronica(String numeroSerieUrnaEletronicaString) {
        this.urnaEletronica.setNumeroSerie(DataTypeConverter.toInteger(numeroSerieUrnaEletronicaString));
    }

    public void setCodigoIdentificacaoCargaUrnaEletronica(String codigoIdentificacaoCarga) {
        this.urnaEletronica.setCodigoIdentificacaoCarga(codigoIdentificacaoCarga);
    }

    public void setVersaoSoftwareUrnaEletronica(String versaoSoftware) {
        this.urnaEletronica.setVersaoSoftware(versaoSoftware);
    }

    public void setDataAberturaUrnaEletronica(String dataAberturaString) {
        this.urnaEletronica.setDataAbertura(DataTypeConverter.toLocalDate(dataAberturaString, FORMATO_DATA));
    }

    public void setHorarioAberturaUrnaEletronica(String horarioAberturaString) {
        this.urnaEletronica.setHorarioAbertura(DataTypeConverter.toLocalTime(horarioAberturaString, FORMATO_HORARIO));
    }

    public void setDataFechamentoUrnaEletronica(String dataFechamentoString) {
        this.urnaEletronica.setDataFechamento(DataTypeConverter.toLocalDate(dataFechamentoString, FORMATO_DATA));
    }

    public void setHorarioFechamentoUrnaEletronica(String horarioFechamentoString) {
        this.urnaEletronica.setHorarioFechamento(DataTypeConverter.toLocalTime(horarioFechamentoString, FORMATO_HORARIO));
    }

    public void setNumeroTSELocalVotacao(String numeroTSELocalVotacaoString) {
        this.localVotacao.setNumeroTSE(DataTypeConverter.toInteger(numeroTSELocalVotacaoString));
    }

    public void setNumeroJuntaApuradora(String numeroJuntaApuradoraString) {
        this.responsavelApuracaoVotos.setNumeroJunta(DataTypeConverter.toInteger(numeroJuntaApuradoraString));
    }

    public void setNumeroTurmaApuradora(String numeroTurmaApuradoraString) {
        this.responsavelApuracaoVotos.setNumeroTurma(DataTypeConverter.toInteger(numeroTurmaApuradoraString));
    }

    public void setDataEmissaoBoletimUrna(String dataEmissaoString) {
        this.emissaoBoletimUrna.setData(DataTypeConverter.toLocalDate(dataEmissaoString, FORMATO_DATA));
    }

    public void setHorarioEmissaoBoletimUrna(String horarioEmissaoString) {
        this.emissaoBoletimUrna.setHorario(DataTypeConverter.toLocalTime(horarioEmissaoString, FORMATO_HORARIO));
    }

    public void addEleicao(String codigoTSEEleicaoString) {
        int codigoTSEEleicao = DataTypeConverter.toInteger(codigoTSEEleicaoString);

        Eleicao eleicao = new Eleicao(codigoTSEEleicao, this.pleito, this.pleito.getAno());

        this.eleicoes.put(new EleicaoId(codigoTSEEleicao), eleicao);

        this.ultimaEleicao = eleicao;
    }

    public void setQuantidadeVotosCargosMajoritariosEleicao(String quantidadeVotosCargosMajoritariosString) {
        if (this.ultimaEleicao == null) return;

        this.ultimaEleicao.setQuantidadeVotosCargosMajoritarios(
            DataTypeConverter.toInteger(quantidadeVotosCargosMajoritariosString)
        );
    }

    public void setQuantidadeVotosCargosProporcionaisEleicao(String quantidadeVotosCargosProporcionaisString) {
        if (this.ultimaEleicao == null) return;

        this.ultimaEleicao.setQuantidadeVotosCargosProporcionais(
            DataTypeConverter.toInteger(quantidadeVotosCargosProporcionaisString)
        );
    }

    public void addCargo(String codigoTSECargoString) {
        int codigoTSECargo = DataTypeConverter.toInteger(codigoTSECargoString);

        Cargo cargo = new Cargo(codigoTSECargo);

        this.cargos.put(new CargoId(codigoTSECargo), cargo);

        this.ultimoCargo = cargo;

        this.cargosEleicoes.put(
            new CargoEleicaoId(codigoTSECargo, this.ultimaEleicao.getCodigoTSE()),
            new CargoEleicao(cargo, this.ultimaEleicao)
        );
    }

    public void setTipoCargo(String codigoTSETipoCargoString) {
        if (this.ultimoCargo == null) return;

        this.ultimoCargo.setTipo(new TipoCargo(DataTypeConverter.toInteger(codigoTSETipoCargoString)));
    }

    public void setVersaoPacoteDadosCargo(String versaoPacoteDadosCargoString) {
        if (this.ultimoCargo == null) return;

        this.ultimoCargo.setVersaoPacoteDados(versaoPacoteDadosCargoString);
    }

    public void setQuantidadeEleitoresAptosCargo(String quantidadeEleitoresAptosString) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeEleitoresAptos = DataTypeConverter.toInteger(quantidadeEleitoresAptosString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeEleitoresAptos(quantidadeEleitoresAptos);
        } else {
            this.apuracoesVotosCargos.put(
                id,
                new ApuracaoVotosCargoEleicao(
                    new CargoEleicao(this.ultimoCargo, this.ultimaEleicao),
                    quantidadeEleitoresAptos
                )
            );
        }
    }

    public void setQuantidadeComparecimentoCargoSemCandidatosCargo(
        String quantidadeComparecimentoCargoSemCandidatosString
    ) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeComparecimentoSemCandidatos = DataTypeConverter.toInteger(
            quantidadeComparecimentoCargoSemCandidatosString
        );

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeComparecimentoCargoSemCandidatos(
                quantidadeComparecimentoSemCandidatos
            );
        }
    }

    public void setQuantidadeVotosNominaisCargo(String quantidadeVotosNominaisString) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeVotosNominais = DataTypeConverter.toInteger(quantidadeVotosNominaisString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeVotosNominais(quantidadeVotosNominais);
        }
    }

    public void setQuantidadeVotosDeLegendaCargo(String quantidadeVotosDeLegendaString) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeVotosDeLegenda = DataTypeConverter.toInteger(quantidadeVotosDeLegendaString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeVotosDeLegenda(quantidadeVotosDeLegenda);
        }
    }

    public void setQuantidadeVotosEmBrancoCargo(String quantidadeVotosEmBrancoString) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeVotosEmBranco = DataTypeConverter.toInteger(quantidadeVotosEmBrancoString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeVotosEmBranco(quantidadeVotosEmBranco);
        }
    }

    public void setQuantidadeVotosNulosCargo(String quantidadeVotosNulosString) {
        if (this.ultimoCargo == null || this.ultimaEleicao == null) return;

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        Integer quantidadeVotosNulos = DataTypeConverter.toInteger(quantidadeVotosNulosString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setQuantidadeVotosNulos(quantidadeVotosNulos);
        }
    }

    public void setTotalVotosApuradosCargo(String totalVotosApuradosCargoString) {
        if (this.ultimoCargo == null) {
            throw new NullPointerException("Não há nenhum cargo a ser vinculada a apuração de votos de cargo.");
        }

        if (this.ultimaEleicao == null) {
            throw new NullPointerException("Não há nenhuma eleição a ser vinculada a apuração de votos de cargo.");
        }

        ApuracaoVotosCargoEleicaoId id = new ApuracaoVotosCargoEleicaoId(
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        Integer totalVotosApuradosCargo = DataTypeConverter.toInteger(totalVotosApuradosCargoString);

        if (this.apuracoesVotosCargos.containsKey(id)) {
            this.apuracoesVotosCargos.get(id).setTotalVotosApurados(totalVotosApuradosCargo);
        }
    }

    public void addPartido(Partido partido) {
        PartidoId id = new PartidoId(partido.getNumeroTSE());

        if (!this.partidos.containsKey(id)) {
            this.partidos.put(id, partido);
        }

        this.ultimoPartido = this.partidos.get(id);
    }

    public void addPartido(String numeroTSEPartidoString) {
        this.addPartido(new Partido(DataTypeConverter.toInteger(numeroTSEPartidoString)));
    }

    public void setQuantidadeVotosDeLegendaPartido(String quantidadeVotosDeLegendaString) {
        if (
            this.ultimoPartido == null ||
            this.ultimoCargo == null ||
            this.ultimaEleicao == null
        ) return;

        ApuracaoVotosPartidoId id = new ApuracaoVotosPartidoId(
            this.ultimoPartido.getNumeroTSE(),
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        int quantidadeVotosDeLegenda = DataTypeConverter.toInteger(quantidadeVotosDeLegendaString);

        if (this.apuracoesVotosPartidos.containsKey(id)) {
            this.apuracoesVotosPartidos.get(id).setQuantidadeVotosDeLegenda(quantidadeVotosDeLegenda);
        } else {
            this.apuracoesVotosPartidos.put(
                id,
                new ApuracaoVotosPartido(
                    this.ultimoPartido,
                    new CargoEleicao(this.ultimoCargo, this.ultimaEleicao),
                    quantidadeVotosDeLegenda
                )
            );
        }
    }

    public void setTotalVotosApuradosPartido(String totalVotosApuradosPartidoString) {
        if (
            this.ultimoPartido == null ||
            this.ultimoCargo == null ||
            this.ultimaEleicao == null
        ) return;

        ApuracaoVotosPartidoId id = new ApuracaoVotosPartidoId(
            this.ultimoPartido.getNumeroTSE(),
            this.ultimoCargo.getCodigoTSE(),
            this.ultimaEleicao.getCodigoTSE()
        );

        Integer totalVotosApurados = DataTypeConverter.toInteger(totalVotosApuradosPartidoString);

        if (this.apuracoesVotosPartidos.containsKey(id)) {
            this.apuracoesVotosPartidos.get(id).setTotalVotosApurados(totalVotosApurados);
        }
    }

    public void addCandidatura(String numeroTSECandidatoString, String totalVotosApuradosString) {
        if (
            this.ultimoPartido == null ||
            this.ultimoCargo == null ||
            this.ultimaEleicao == null
        ) return;

        int totalVotosApurados = DataTypeConverter.toInteger(totalVotosApuradosString);

        Partido partido;

        if (CODIGOS_TSE_CARGOS_COM_APENAS_UM_CANDIDATO_POR_PARTIDO.contains(this.ultimoCargo.getCodigoTSE())) {
            int numeroTSEPartido = DataTypeConverter.toInteger(StringUtils.left(numeroTSECandidatoString, 2));
            PartidoId partidoId = new PartidoId(numeroTSEPartido);

            if (this.partidos.containsKey(partidoId)) {
                partido = this.partidos.get(partidoId);
            } else {
                partido = new Partido(numeroTSEPartido);
                this.addPartido(partido);
            }

            this.apuracoesVotosPartidos.put(
                new ApuracaoVotosPartidoId(
                    numeroTSEPartido,
                    this.ultimoCargo.getCodigoTSE(),
                    this.ultimaEleicao.getCodigoTSE()
                ),
                new ApuracaoVotosPartido(
                    partido,
                    new CargoEleicao(this.ultimoCargo, this.ultimaEleicao),
                    0,
                    totalVotosApurados
                )
            );
        } else {
            partido = this.ultimoPartido;
        }

        int numeroTSECandidato = DataTypeConverter.toInteger(numeroTSECandidatoString);

        Candidatura candidatura = new Candidatura(
            numeroTSECandidato,
            new CargoEleicao(this.ultimoCargo, this.ultimaEleicao),
            partido
        );

        this.candidaturas.put(
            new CandidaturaId(
                numeroTSECandidato,
                this.ultimoCargo.getCodigoTSE(),
                this.ultimaEleicao.getCodigoTSE()
            ),
            candidatura
        );

        this.apuracoesVotosCandidaturas.put(
            new ApuracaoVotosCandidaturaId(
                numeroTSECandidato,
                this.ultimoCargo.getCodigoTSE(),
                this.ultimaEleicao.getCodigoTSE()
            ),
            new ApuracaoVotosCandidatura(
                candidatura,
                totalVotosApurados
            )
        );
    }

    public void finalizarMapeamentoRelacionamentos() {
        this.pleito.setProcessoEleitoral(this.processoEleitoral);

        this.municipio.setUF(this.uf);

        this.zona.setUF(this.uf);

        this.localVotacao.setMunicipio(this.municipio);
        this.localVotacao.setZona(this.zona);

        this.secao.setZona(this.zona);
        this.secao.setMunicipio(this.municipio);

        this.secaoProcessoEleitoral.setSecao(this.secao);
        this.secaoProcessoEleitoral.setProcessoEleitoral(this.processoEleitoral);
        this.secaoProcessoEleitoral.setLocalVotacao(this.localVotacao);

        this.secaoPleito.setSecao(this.secao);
        this.secaoPleito.setPleito(this.pleito);
    }
}
