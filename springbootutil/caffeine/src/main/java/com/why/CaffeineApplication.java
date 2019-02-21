package com.why;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaffeineApplication {


    //private static Logger logger = LoggerFactory.getLogger(MySpringBootApplication.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        //logger.info("args: {}", args);
        System.out.println(objectMapper.writeValueAsString(args));

        String env = "test";
        if (args.length > 0) {
            String[] profile = args[0].split("=");
            if (profile.length > 1) {
                env = profile[1];
            }
        }

        String consoleLevel = "trace";
        String whyLevel = "trace";

        switch (env) {
            case "test":
                consoleLevel = "warn";
                whyLevel = "trace";
                break;
            case "prod":
                consoleLevel = "warn";
                whyLevel = "info";
                break;
            default:

        }

        MainMapLookup.setMainArguments("projectName", "test-project-name",
                "consoleLevel", consoleLevel,
                "whyLevel", whyLevel);
        SpringApplication.run(CaffeineApplication.class, args);
        //logger.warn("My Spring Boot Application Started");
     }

    }