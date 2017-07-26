package com.uw.challenge;

import com.uw.challenge.repository.ConnectedCouplesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

import static java.lang.System.exit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private NetworkImpl networkImpl;


    public static void main(String[] args) throws Exception {
        log.info("running Application");
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    static class ApplicationConfiguration {

        @Bean
        public NetworkImpl networkImpl() {
            return new NetworkImpl();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {

        networkImpl.printAllInformationFromDB();
        exit(0);
    }

}