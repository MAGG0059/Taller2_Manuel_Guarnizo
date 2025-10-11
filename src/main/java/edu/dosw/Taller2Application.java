package edu.dosw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.dosw", "edu.dosw.controller"})
public class Taller2Application {
    public static void main(String[] args) {
        SpringApplication.run(Taller2Application.class, args);
    }
}