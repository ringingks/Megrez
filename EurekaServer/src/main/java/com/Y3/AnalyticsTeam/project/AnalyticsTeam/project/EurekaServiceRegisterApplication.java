package com.Y3.AnalyticsTeam.project.AnalyticsTeam.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceRegisterApplication.class, args);
    }
}
