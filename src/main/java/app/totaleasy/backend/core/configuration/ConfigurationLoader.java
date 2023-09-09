package app.totaleasy.backend.core.configuration;

import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import app.totaleasy.backend.core.exception.FalhaCarregamentoArquivoConfiguracaoException;

public class ConfigurationLoader {

    private ConfigurationLoader() {

    }

    public static Configuration getConfiguration(String filename) {
        try (InputStream inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream(filename)) {
            LoaderOptions loaderOptions = new LoaderOptions();

            loaderOptions.setAllowDuplicateKeys(false);
            loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
            loaderOptions.setAllowRecursiveKeys(true);

            return new Yaml().load(inputStream);
        } catch (IOException exception) {
            throw new FalhaCarregamentoArquivoConfiguracaoException(
                "Não foi possível carregar o arquivo de configuração da aplicação."
            );
        }
    }
}
