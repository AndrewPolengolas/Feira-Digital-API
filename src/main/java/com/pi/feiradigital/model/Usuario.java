package com.pi.feiradigital.model;

import com.pi.feiradigital.model.type.Funcao;
import com.pi.feiradigital.model.type.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "login"
        })
})
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dataIni;

    private LocalDateTime dataAlt;

    private LocalDateTime dataFim;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Funcao funcao;

    public Usuario() {
    }

}
