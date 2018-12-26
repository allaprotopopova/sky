package protopopova.alla;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import protopopova.alla.model.Collocation;
import protopopova.alla.repository.CollocationRepository;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.debug("enter main");
        SpringApplication.run(Application.class);
        log.debug("exit main");
    }

}
