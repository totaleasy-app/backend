package app.totaleasy.backend.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TotalEasyBackend {

    public static void main(String[] args) {
        SpringApplication.run(TotalEasyBackend.class, args);
    }
}
