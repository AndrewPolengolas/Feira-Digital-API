package com.pi.feiradigital.model;

import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.model.type.TipoEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Status status;

    private LocalDateTime dataIni;

    private LocalDateTime dataAlt;

    private LocalDateTime dataFim;

    private String cep;

    private String logradouro;

    private String complemento;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;

    private Long numero;

    private String cidade;

    private String estado;

    private String pais;

}
