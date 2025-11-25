package ch.makery.vs24javamywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Vs24JavaMyWebAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        SpringApplication.run(Vs24JavaMyWebAppApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Vs24JavaMyWebAppApplication.class);
    }
}
