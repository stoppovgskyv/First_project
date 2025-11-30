package com.concessionaria.concessionaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.concessionaria.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.concessionaria.model.Carro;
import com.concessionaria.concessionaria.repository.CarroRepository;

/**
 * Camada de serviço responsável pela LÓGICA DE NEGÓCIO dos carros.
 *
 * Aqui ficam: - regras e validações adicionais, - chamadas ao repositório
 * (persistência), - conversões entre DTOs e entidades, - tratamento de erros.
 *
 * O controller NUNCA deve acessar o banco diretamente — tudo passa por aqui.
 */
@Service
public class CarroService {

    private final CarroRepository repository;

    /**
     * Injeta o repositório no service. O Spring cria automaticamente o
     * CarroRepository e entrega aqui.
     */
    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    // ===============================================================
    // Criar carro
    // ===============================================================
    /**
     * Cria um novo carro no banco de dados a partir dos dados vindos do DTO.
     */
    public CarroResponseDTO criar(CarroRequestDTO dto) {

        // Cria a entidade Carro a partir do DTO
        Carro carro = new Carro();
        carro.setModelo(dto.getModelo());
        carro.setMarca(dto.getMarca());
        carro.setAno(dto.getAno());
        carro.setPreco(dto.getPreco());

        // Salva no banco e recebe a entidade já persistida
        Carro salvo = repository.save(carro);

        // Converte para DTO de resposta
        return toResponse(salvo);
    }

    // ===============================================================
    // Listar todos
    // ===============================================================
    /**
     * Busca todos os carros cadastrados no banco.
     */
    public List<CarroResponseDTO> listarTodos() {
        List<Carro> carros = repository.findAll();

        // Converte lista de entidades para lista de DTOs
        return carros.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ===============================================================
    // Buscar por ID
    // ===============================================================
    /**
     * Busca um carro pelo ID.
     *
     * @throws RuntimeException se o carro não existir
     */
    public CarroResponseDTO buscarPorId(Long id) {

        // Busca o carro ou lança exception caso não exista
        Carro carro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com id: " + id));

        return toResponse(carro);
    }

    // ===============================================================
    // Atualizar
    // ===============================================================
    /**
     * Atualiza os dados de um carro existente.
     */
    public CarroResponseDTO atualizar(Long id, CarroRequestDTO dto) {

        // Busca a entidade existente no banco
        Carro carro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com id: " + id));

        // Atualiza somente campos editáveis
        carro.setModelo(dto.getModelo());
        carro.setMarca(dto.getMarca());
        carro.setAno(dto.getAno());
        carro.setPreco(dto.getPreco());

        // Salva alterações no banco
        Carro atualizado = repository.save(carro);

        return toResponse(atualizado);
    }

    // ===============================================================
    // Deletar
    // ===============================================================
    /**
     * Deleta um carro pelo ID.
     *
     * @throws RuntimeException se o carro não existir
     */
    public void deletar(Long id) {

        // Confere se existe antes de deletar
        if (!repository.existsById(id)) {
            throw new RuntimeException("Carro não encontrado com id: " + id);
        }

        repository.deleteById(id);
    }

    // ===============================================================
    // Conversão Entidade -> DTO de resposta
    // ===============================================================
    /**
     * Converte uma entidade Carro em um CarroResponseDTO. Essa separação
     * garante: - proteção da entidade (não expor diretamente), - controle total
     * sobre o que é exibido ao usuário.
     */
    private CarroResponseDTO toResponse(Carro carro) {
        CarroResponseDTO resp = new CarroResponseDTO();
        resp.setId(carro.getId());
        resp.setModelo(carro.getModelo());
        resp.setMarca(carro.getMarca());
        resp.setAno(carro.getAno());
        resp.setPreco(carro.getPreco());
        return resp;
    }
}
