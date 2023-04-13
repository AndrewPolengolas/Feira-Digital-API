package com.pi.feiradigital.repository;

import com.pi.feiradigital.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    @Query(value = "SELECT * FROM usuarios u JOIN FETCH u.roles WHERE u.login = :login", nativeQuery = true)
    Usuario findByLoginFetchRoles(@Param("login") String login);
}
