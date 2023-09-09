package app.totaleasy.backend.core.service;

import java.io.IOException;

import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import app.totaleasy.backend.core.exception.FalhaRequisicaoWebException;
import app.totaleasy.backend.core.model.complemento.BoletimUrna;
import app.totaleasy.backend.core.util.WebRequest;

public class ComplementoBoletimUrnaCaching extends Redis<String, BoletimUrna> {

    private static final Gson gson = new GsonBuilder().create();

    public BoletimUrna get(String url) {
        try {
            if (redisConnection.exists(url)) {
                return gson.fromJson(redisConnection.get(url), BoletimUrna.class);
            }

            JsonObject jsonObject = WebRequest.getJSON(url);

            BoletimUrna complementoBoletimUrna = gson.fromJson(jsonObject, BoletimUrna.class);

            redisConnection.set(url, gson.toJson(complementoBoletimUrna));

            return complementoBoletimUrna;
        } catch (IOException | URISyntaxException exception) {
            throw new FalhaRequisicaoWebException(String.format("Não foi possível obter os dados complementares do boletim de urna através da URL \"%s\".", url));
        }
    }
}
