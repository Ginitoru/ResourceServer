package com.iordache.web;

import com.iordache.security.ResourceServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan({"com.iordache.web.*", "com.iordache.persistence.*"})
@Import({ResourceServerConfiguration.class})
@EntityScan({"com.iordache.domain.entity"})
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }



}
