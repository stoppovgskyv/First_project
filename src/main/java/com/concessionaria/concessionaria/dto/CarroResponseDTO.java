package com.concessionaria.concessionaria.dto;

import lombok.Data;

/**
 * DTO responsável por transportar dados do Carro DA aplicação PARA fora dela
 * (respostas).
 *
 * Diferente do CarroRequestDTO (entrada de dados), este DTO é usado para: -
 * retornar dados aos controllers, - exibir informações nas páginas HTML
 * (Thymeleaf),
 */
@Data // Lombok: gera automaticamente getters, setters, equals, hashCode e toString
public class CarroResponseDTO {

    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private Double preco;
}
