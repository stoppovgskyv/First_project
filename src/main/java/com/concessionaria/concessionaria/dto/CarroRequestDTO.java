package com.concessionaria.concessionaria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO (Data Transfer Object) utilizado para receber dados do formulário de
 * criação ou edição de um carro.
 *
 * Ele é usado APENAS para entrada de dados (request), garantindo que o usuário
 * envie todas as informações necessárias antes de o serviço criar ou atualizar
 * a entidade Carro no banco.
 *
 * As anotações de validação garantem que os valores recebidos estejam corretos
 * antes de seguir para a lógica de negócio.
 */
@Data // Lombok: gera getters, setters, equals, hashCode e toString automaticamente
public class CarroRequestDTO {

    /**
     * Modelo do carro.
     *
     * @NotBlank garante que não seja nulo, vazio ou só espaços.
     */
    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    /**
     * Marca do carro. Também não pode ser vazio.
     */
    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    /**
     * Ano de fabricação.
     *
     * @NotNull garante que o campo tenha sido preenchido.
     */
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    /**
     * Preço do carro.
     *
     * @NotNull exige que o usuário informe o valor.
     * @Min assegura que o preço não seja negativo.
     */
    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço deve ser positivo")
    private Double preco;

}
