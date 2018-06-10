package com.Y3.AnalyticsTeam.CT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Boot 应用启动类
 *
 */
// Spring Boot 作为一般jar启动
//@SpringBootApplication
//public class Application {
//
//    public static void main(String[] args) {
//        // 程序启动入口
//        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
//        SpringApplication.run(Application.class,args);
//    }
//
//}

// spring boot 作为war启动
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
