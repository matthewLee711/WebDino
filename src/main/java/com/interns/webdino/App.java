package com.interns.webdino;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={"com.interns.webdino"})
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static ConfigurableApplicationContext ctx;

    public static ConfigurableApplicationContext getCtx() {
		return ctx;
	}

    public static void main(String[] args) {

        LOGGER.info("Starting the app...");
        ctx = SpringApplication.run(App.class, args);

    }

}
