package com.example.back_end.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JavaPorts  implements CommandLineRunner {

    @Value("${server.port:8081}")
    private String SERVER_PORT;

    @Override
    public void run(String[] args) throws Exception {
        String hTwo = "http://localhost:" + SERVER_PORT + "/h2-console";
        String swagger = "http://localhost:" + SERVER_PORT + "/swagger-ui/index.html#/";

        System.out.println("\n--------------------------------");
        System.out.println("H2 Console: " + hTwo);
        System.out.println("Swagger UI: " + swagger);
        System.out.println("----------------------------------\n");
    }
}
