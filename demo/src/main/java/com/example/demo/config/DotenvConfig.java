//package com.example.demo.config;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DotenvConfig {
//
//    @Bean
//    public void loadEnv() {
//        // Charger le fichier .env
//        Dotenv dotenv = Dotenv.configure().load();
//
//        // Définir les propriétés système pour Spring Boot
//        System.setProperty("SPRING_PROFILE", dotenv.get("SPRING_PROFILE", "h2")); // Par défaut : h2
//        System.setProperty("DB_URL", dotenv.get("DB_URL", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"));
//        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME", "sa")); // Valeur par défaut pour H2
//        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD", "")); // Valeur par défaut pour H2
//        System.setProperty("DB_DIALECT", dotenv.get("DB_DIALECT", "org.hibernate.dialect.H2Dialect"));
//        System.setProperty("DB_DDL_AUTO", dotenv.get("DB_DDL_AUTO", "update"));
//    }
//}
