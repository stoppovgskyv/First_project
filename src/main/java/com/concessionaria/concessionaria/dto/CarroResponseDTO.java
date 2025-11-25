package com.concessionaria.concessionaria.dto;

import lombok.Data;

@Data
public class CarroResponseDTO {

    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private Double preco;
}
