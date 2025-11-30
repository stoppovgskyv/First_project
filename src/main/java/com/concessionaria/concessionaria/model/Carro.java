package com.concessionaria.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Entidade JPA que representa a tabela "carro" no banco de dados.
 *
 * Cada instância desta classe corresponde a um registro na tabela.
 */
@Data // Lombok: gera getters, setters, equals, hashCode e toString
@Entity // Indica que esta classe é uma entidade JPA e vai virar uma tabela
public class Carro {

    /**
     * ID gerado automaticamente pelo banco de dados.
     *
     * GenerationType.IDENTITY = utiliza auto-increment do MySQL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotNull(message = "O ano é obrigatório")
    private int ano;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço deve ser positivo")
    private double preco;

}
