package com.Y3.AnalyticsTeam.CT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommonProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonProviderApplication.class, args);
    }

}
