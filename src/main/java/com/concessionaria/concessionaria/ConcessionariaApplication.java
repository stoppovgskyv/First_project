package com.concessionaria.concessionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcessionariaApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * SpringApplication.run(): - carrega o contexto do Spring - ativa o
     * auto-configuration - inicia o servidor Tomcat (porta padrão 8080) - deixa
     * a aplicação no ar
     */
    public static void main(String[] args) {
        SpringApplication.run(ConcessionariaApplication.class, args);
    }

}
