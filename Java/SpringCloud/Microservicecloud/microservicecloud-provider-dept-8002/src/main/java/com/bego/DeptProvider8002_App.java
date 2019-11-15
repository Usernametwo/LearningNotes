package com.bego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient           //服务注册
@EnableDiscoveryClient        //服务发现
public class DeptProvider8002_App {

    public static void main(String[] args) {
        new SpringApplication(DeptProvider8002_App.class).run(args);
    }
}
