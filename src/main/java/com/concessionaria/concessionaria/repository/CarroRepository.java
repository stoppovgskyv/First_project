package com.concessionaria.concessionaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concessionaria.concessionaria.model.Carro;

/**
 * Repositório responsável por acessar o banco de dados da entidade Carro.
 *
 * Ele estende JpaRepository, que automaticamente fornece: - salvar () -
 * atualizar () - deletar () - buscar por ID () - listar todos () - paginação
 * (Pageable) - ordenação (Sort)
 *
 * Isso elimina a necessidade de escrever consultas SQL ou JPQL manuais para
 * operações básicas.
 */
public interface CarroRepository extends JpaRepository<Carro, Long> {

    /**
     * Busca carros cujo modelo contém o texto informado, sem diferenciar
     * maiúsculas/minúsculas.
     *
     * O Spring Data JPA interpreta automaticamente o nome do método: - findBy =
     * buscar por - Modelo = campo da entidade Carro - Containing = LIKE %texto%
     * - IgnoreCase = sem diferenciar letras maiúsculas/minúsculas
     */
    List<Carro> findByModeloContainingIgnoreCase(String modelo);

    /**
     * Busca carros pela marca, também usando filtro parcial e ignorando
     * maiúsculas/minúsculas.
     */
    List<Carro> findByMarcaContainingIgnoreCase(String marca);
}
