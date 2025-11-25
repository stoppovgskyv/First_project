package com.concessionaria.concessionaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concessionaria.concessionaria.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByModeloContainingIgnoreCase(String modelo);

    List<Carro> findByMarcaContainingIgnoreCase(String marca);
}
