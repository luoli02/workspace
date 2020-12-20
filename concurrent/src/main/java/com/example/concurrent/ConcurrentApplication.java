package com.example.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author luoli
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConcurrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrentApplication.class, args);
    }

}
