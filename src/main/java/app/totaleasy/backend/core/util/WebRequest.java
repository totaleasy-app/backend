package app.totaleasy.backend.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WebRequest {

    private static final String GET_METHOD_NAME = "GET";

    private WebRequest() {

    }

    public static JsonObject getJSON(String urlString) throws IOException, URISyntaxException {
        URL url = new URI(urlString).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(GET_METHOD_NAME);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines().collect(Collectors.joining());

        connection.disconnect();

        JsonElement jsonElement = JsonParser.parseString(response);

        return jsonElement.getAsJsonObject();
    }

    public static byte[] getBytes(String urlString) throws IOException, URISyntaxException {
        URL url = new URI(urlString).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(GET_METHOD_NAME);

        byte[] bytes = connection.getInputStream().readAllBytes();

        connection.disconnect();

        return bytes;
    }
}
