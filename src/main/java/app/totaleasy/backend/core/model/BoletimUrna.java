package app.totaleasy.backend.core.model;

import java.security.NoSuchAlgorithmException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import app.totaleasy.backend.core.configuration.ConfigurationLoader;
import app.totaleasy.backend.core.exception.AssinaturasDivergentesException;
import app.totaleasy.backend.core.exception.DadosFaltantesException;
import app.totaleasy.backend.core.exception.FalhaVerificacaoAssinaturaException;
import app.totaleasy.backend.core.exception.FalhaVerificacaoConteudoException;
import app.totaleasy.backend.core.exception.HashsDivergentesException;
import app.totaleasy.backend.core.exception.OrdemIncorretaQRCodeBoletimUrnaException;
import app.totaleasy.backend.core.exception.QRCodeFaltanteException;
import app.totaleasy.backend.core.exception.VersoesChaveAssinaturaDivergentesException;
import app.totaleasy.backend.core.service.ChavePublicaVerificacaoAssinaturaCaching;
import app.totaleasy.backend.core.service.ComplementoBoletimUrnaCaching;
import app.totaleasy.backend.core.util.Security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
public class BoletimUrna {

    private List<QRCodeBoletimUrna> qrCodes;

    private ConteudoQRCodeBoletimUrna conteudo;

    private String assinatura;

    @Getter(value = AccessLevel.NONE)
    private final ChavePublicaVerificacaoAssinaturaCaching chavePublicaVerificacaoAssinaturaCacheService;

    @Getter(value = AccessLevel.NONE)
    private final ComplementoBoletimUrnaCaching complementoBoletimUrnaCacheService;

    public BoletimUrna() {
        this.qrCodes = new LinkedList<>();
        this.chavePublicaVerificacaoAssinaturaCacheService = new ChavePublicaVerificacaoAssinaturaCaching();
        this.complementoBoletimUrnaCacheService = new ComplementoBoletimUrnaCaching();
    }

    public List<QRCodeBoletimUrna> getQRCodes() {
        return this.qrCodes;
    }

    public void setQRCodes(List<QRCodeBoletimUrna> qrCodes) {
        this.qrCodes = qrCodes;
    }

    public void addQRCode(QRCodeBoletimUrna qrCode) {
        this.qrCodes.add(qrCode);
    }

    public int getQuantidadeTotalQRCodes() {
        return this.qrCodes.get(0).getCabecalho().getMarcaInicioDados().getQuantidadeTotalQRCodes();
    }

    private void validarDadosURLChavePublicaVerificacaoAssinatura() throws DadosFaltantesException {
        if (this.qrCodes == null || this.qrCodes.isEmpty()) {
            throw new QRCodeFaltanteException("O boletim de urna deve ter pelo menos um QR code.");
        }

        Map<String, String> dadosFaltantes = new LinkedHashMap<>();

        QRCodeBoletimUrna primeiroQRCode = this.qrCodes.get(0);

        if (primeiroQRCode == null || primeiroQRCode.getCabecalho() == null) {
            dadosFaltantes.put("VERSAO_CHAVE", "Versão das chaves de assinatura do(s) QR Code(s).");
        }

        Set<Integer> versoesChaveAssinaturaQRCode = new HashSet<>();

        for (QRCodeBoletimUrna qrCode : this.qrCodes) {
            if (
                versoesChaveAssinaturaQRCode.add(qrCode.getCabecalho().getVersaoChaveAssinatura()) &&
                qrCode.getCabecalho().getMarcaInicioDados().getIndiceQRCode() != 1
            ) {
                throw new VersoesChaveAssinaturaDivergentesException("As versões da chave utilizada para assinar os QR codes do boletim de urna são divergentes.");
            }
        }

        if (
            this.conteudo.getProcessoEleitoral() == null ||
            this.conteudo.getProcessoEleitoral().getOrigemConfiguracao() == null
        ) {
            dadosFaltantes.put("ORLC", "Origem da configuração do processo eleitoral.");
        }

        if (this.conteudo.getFase() == null || this.conteudo.getFase().getNomeAbreviado() == null) {
            dadosFaltantes.put("F", "Primeira letra da fase dos dados em minúsculo.");
        }

        if (this.conteudo.getUF() == null) {
            dadosFaltantes.put("UF", "Sigla da UF em minúsculo.");
        }

        if (!dadosFaltantes.isEmpty()) {
            String mensagem = "Não foi possível compor a URL de verificação da assinatura do boletim de urna porque um ou mais dados que a compõe não foram informados ou são inválidos.";
            throw new DadosFaltantesException(mensagem, dadosFaltantes);
        }
    }

    private String comporURLChavePublicaVerificacaoAssinatura() throws DadosFaltantesException {
        this.validarDadosURLChavePublicaVerificacaoAssinatura();

        int versaoChaveAssinaturaQRCode = this.getQRCodes().get(0).getCabecalho().getVersaoChaveAssinatura();
        String origem = this.conteudo.getProcessoEleitoral().getOrigemConfiguracao().toStringURLVerificacaoAssinatura();
        String fase = this.conteudo.getFase().getNomeAbreviado().toLowerCase();
        String uf = this.conteudo.getUF().getSigla().toLowerCase();

        String urlBaseVerificacaoAssinatura = ConfigurationLoader.getConfiguration("core.yaml").getURLs().getURLVerificacaoAssinatura();

        return String.format("%s/%s/%s/%s%sqrcode.pub", urlBaseVerificacaoAssinatura, versaoChaveAssinaturaQRCode, origem, fase, uf);
    }

    private void validarDadosURLDadosComplementares() throws DadosFaltantesException {
        Map<String, String> dadosFaltantes = new LinkedHashMap<>();

        if (this.conteudo.getFase() == null) {
            dadosFaltantes.put("fase", "Fase dos dados por extenso e em minúsculo.");
        }

        if (this.conteudo.getProcessoEleitoral() == null) {
            dadosFaltantes.put("idProcesso", "Número do processo eleitoral.");
        }

        if (this.conteudo.getFase() == null || this.conteudo.getFase().getNomeAbreviado() == null) {
            dadosFaltantes.put("F", "Primeira letra da fase dos dados em minúsculo.");
        }

        if (this.conteudo.getPleito() == null) {
            dadosFaltantes.put("ppppp", "Número do pleito com zeros à esquerda, totalizando 5 dígitos.");
        }

        if (this.conteudo.getUF() == null) {
            dadosFaltantes.put("UF", "Sigla da UF em minúsculo.");
        }

        if (this.conteudo.getMunicipio() == null) {
            dadosFaltantes.put("MMMMM", "Número do município com zeros à esquerda, totalizando 5 dígitos.");
        }

        if (!dadosFaltantes.isEmpty()) {
            String mensagem = "Não foi possível compor a URL de busca dos dados complementares do boletim de urna porque um ou mais dados que a compõe não foram informados ou são inválidos.";
            throw new DadosFaltantesException(mensagem, dadosFaltantes);
        }
    }

    private String comporURLDadosComplementares() throws DadosFaltantesException {
        this.validarDadosURLDadosComplementares();

        String fase = this.conteudo.getFase().getNome().toLowerCase();
        String idProcesso = Integer.toString(this.conteudo.getProcessoEleitoral().getCodigoTSE());
        String f = this.conteudo.getFase().getNomeAbreviado().toLowerCase();
        String ppppp = String.format("%05d", this.conteudo.getPleito().getCodigoTSE());
        String uf = this.conteudo.getUF().getSigla().toLowerCase();
        String mmmmm = String.format("%05d", this.conteudo.getMunicipio().getCodigoTSE());

        String urlBaseDadosComplementares = ConfigurationLoader.getConfiguration("core.yaml").getURLs().getURLDadosComplementares();

        return String.format("%s/%s/%s/%s%s%s%s-qbu.js", urlBaseDadosComplementares, fase, idProcesso, f, ppppp, uf, mmmmm);
    }

    public app.totaleasy.backend.core.model.complemento.BoletimUrna getDadosComplementares() {
        return this.complementoBoletimUrnaCacheService.get(this.comporURLDadosComplementares());
    }

    private void verificarQRCodes() {
        if (this.qrCodes == null || this.qrCodes.isEmpty()) {
            throw new QRCodeFaltanteException("O boletim de urna deve ter pelo menos um QR code.");
        }

        Iterator<QRCodeBoletimUrna> iterator = this.qrCodes.iterator();

        int ultimoIndice = 0;

        while (iterator.hasNext()) {
            int indice = iterator.next().getCabecalho().getMarcaInicioDados().getIndiceQRCode();

            if (indice <= ultimoIndice) {
                throw new OrdemIncorretaQRCodeBoletimUrnaException("Os QR codes devem ser informados na mesma ordem em que aparecem no boletim de urna.");
            } else if (indice - ultimoIndice != 1) {
                throw new QRCodeFaltanteException(String.format("Está faltando um QR code entre os QR codes %d e %d.", ultimoIndice, indice));
            }

            ultimoIndice = indice;
        }
    }

    private String verificarConteudo() {
        if (this.qrCodes == null || this.qrCodes.isEmpty()) {
            throw new QRCodeFaltanteException("O boletim de urna deve ter pelo menos um QR code.");
        }

        try {
            String ultimoPayloadConteudo = null;
            String ultimoHashCalculado = null;

            StringJoiner dadosAcumulados = new StringJoiner(" ");

            for (QRCodeBoletimUrna qrCode : this.qrCodes) {
                int indice = qrCode.getCabecalho().getMarcaInicioDados().getIndiceQRCode();

                if (indice == 1) {
                    ultimoHashCalculado = Security.sha512(qrCode.getPayloadConteudo());
                } else {
                    dadosAcumulados.add(String.format("%s HASH:%s", ultimoPayloadConteudo, ultimoHashCalculado));
                    ultimoHashCalculado = Security.sha512(String.format("%s %s", dadosAcumulados, qrCode.getPayloadConteudo()));
                }

                if (!ultimoHashCalculado.equalsIgnoreCase(qrCode.getHash())) {
                    throw new HashsDivergentesException(String.format("O hash do QR code %d difere do calculado, ou seja, seu conteúdo foi adulterado.", indice));
                }

                ultimoPayloadConteudo = qrCode.getPayloadConteudo();
            }

            return ultimoHashCalculado;
        } catch (NoSuchAlgorithmException exception) {
            throw new FalhaVerificacaoConteudoException("Não foi possível apurar o conteúdo do boletim de urna.");
        }
    }

    private void verificarAssinatura() {
        try {
            String url = this.comporURLChavePublicaVerificacaoAssinatura();
            byte[] chavePublica = this.chavePublicaVerificacaoAssinaturaCacheService.get(url);
            String hashUltimoQRCode = this.verificarConteudo();

            if (!Security.verifySignature(hashUltimoQRCode, this.assinatura, chavePublica)) {
                throw new AssinaturasDivergentesException("A assinatura digital do QR code difere da original, ou seja, o conteúdo do boletim de urna foi adulterado.");
            }
        } catch (Exception exception) {
            throw new FalhaVerificacaoAssinaturaException("Não foi possível verificar a assinatura digital do boletim de urna.");
        }
    }

    public void verificar() {
        this.verificarQRCodes();
        this.verificarAssinatura();
    }
}
