package com.concessionaria.concessionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 *
 * A anotação @SpringBootApplication é uma composição de: - @Configuration :
 * permite registrar beans no contexto do Spring - @EnableAutoConfiguration :
 * ativa as configurações automáticas do Spring Boot - @ComponentScan : habilita
 * a varredura automática de componentes (Controllers, Services, Repositories,
 * etc.)
 *
 * Isso significa que o Spring irá procurar automaticamente todas as classes
 * anotadas dentro do pacote "com.concessionaria.concessionaria" e subpacotes,
 * criando e configurando os componentes necessários.
 *
 * O método main() é o ponto de entrada da aplicação: - Inicializa o Spring Boot
 * - Configura o servidor embutido (Tomcat) - Sobe o contexto de aplicação -
 * Disponibiliza as rotas web e conexões com o banco
 */
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
