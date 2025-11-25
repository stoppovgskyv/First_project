package com.concessionaria.concessionaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.concessionaria.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.concessionaria.model.Carro;
import com.concessionaria.concessionaria.repository.CarroRepository;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    // Criar carro
    public CarroResponseDTO criar(CarroRequestDTO dto) {
        Carro carro = new Carro();
        carro.setModelo(dto.getModelo());
        carro.setMarca(dto.getMarca());
        carro.setAno(dto.getAno());
        carro.setPreco(dto.getPreco());

        Carro salvo = repository.save(carro);
        return toResponse(salvo);
    }

    // Listar todos
    public List<CarroResponseDTO> listarTodos() {
        List<Carro> carros = repository.findAll();
        return carros.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Buscar por id
    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com id: " + id));
        return toResponse(carro);
    }

    // Atualizar
    public CarroResponseDTO atualizar(Long id, CarroRequestDTO dto) {
        Carro carro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com id: " + id));

        carro.setModelo(dto.getModelo());
        carro.setMarca(dto.getMarca());
        carro.setAno(dto.getAno());
        carro.setPreco(dto.getPreco());

        Carro atualizado = repository.save(carro);
        return toResponse(atualizado);
    }

    // Deletar
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Carro não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }

    // Conversão entidade -> response DTO
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
