package app.totaleasy.backend.core.service;

import java.io.IOException;

import java.net.URISyntaxException;

import org.bouncycastle.util.encoders.Hex;

import app.totaleasy.backend.core.exception.FalhaRequisicaoWebException;
import app.totaleasy.backend.core.util.WebRequest;

public class ChavePublicaVerificacaoAssinaturaCaching extends Redis<String, byte[]> {

    public byte[] get(String url) {
        try {
            if (redisConnection.exists(url)) {
                return Hex.decode(redisConnection.get(url));
            }

            byte[] chavePublica = WebRequest.getBytes(url);

            redisConnection.set(url, Hex.toHexString(chavePublica).toUpperCase());

            return chavePublica;
        } catch (IOException | URISyntaxException exception) {
            throw new FalhaRequisicaoWebException(
                String.format(
                    "Não foi possível obter a chave pública para verificar a assinatura digital do boletim de urna através da URL \"%s\".",
                    url
                )
            );
        }
    }
}
