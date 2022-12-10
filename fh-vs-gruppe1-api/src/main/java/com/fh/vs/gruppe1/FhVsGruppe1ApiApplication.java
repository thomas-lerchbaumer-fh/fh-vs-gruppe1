package com.fh.vs.gruppe1;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FhVsGruppe1ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FhVsGruppe1ApiApplication.class, args);
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>Ready to go<<<<<<<<<<<<<<<<<<<<<<<<<");

    }

}
