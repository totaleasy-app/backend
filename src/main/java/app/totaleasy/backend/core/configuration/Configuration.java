package app.totaleasy.backend.core.configuration;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Configuration {

    private RedisConfiguration redis;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private URLConfiguration urls;

    public URLConfiguration getURLs() {
        return this.urls;
    }

    public void setURLs(URLConfiguration urls) {
        this.urls = urls;
    }
}
