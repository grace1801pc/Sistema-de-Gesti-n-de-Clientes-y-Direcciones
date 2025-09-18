package com.example.demo.repository;

import com.example.demo.model.DireccionCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DireccionClienteRepository extends JpaRepository<DireccionCliente, Long> {
    List<DireccionCliente> findByClienteId(Long clienteId);
    DireccionCliente findByClienteIdAndMatrizTrue(Long clienteId);
}
