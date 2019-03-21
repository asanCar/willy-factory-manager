package com.wonka.rrhh.oompamanager;

import com.wonka.rrhh.oompamanager.config.constants.SpringConfigConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {SpringConfigConstants.BASE_PACKAGE})
public class OompaManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(OompaManagerApplication.class, args);
    }

}
