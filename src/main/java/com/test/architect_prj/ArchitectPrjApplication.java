package com.test.architect_prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArchitectPrjApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ArchitectPrjApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ArchitectPrjApplication.class, args);
    }

}
