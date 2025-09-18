package com.example.demo.repository;



import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByNumeroIdentificacion(String numeroIdentificacion);

    List<Cliente> findByNumeroIdentificacionContainingIgnoreCaseOrNombresContainingIgnoreCase(String numeroIdentificacion, String nombres);
}

