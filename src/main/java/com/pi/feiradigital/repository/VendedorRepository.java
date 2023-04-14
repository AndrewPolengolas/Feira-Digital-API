package com.pi.feiradigital.repository;

import com.pi.feiradigital.model.Cliente;
import com.pi.feiradigital.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Vendedor findByUsuario(Long usuario);
}
