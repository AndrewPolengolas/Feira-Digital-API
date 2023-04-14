package com.pi.feiradigital.repository;

import com.pi.feiradigital.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByUsuario(Long usuario);
}
