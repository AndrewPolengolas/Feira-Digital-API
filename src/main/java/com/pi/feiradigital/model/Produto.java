package com.pi.feiradigital.model;

import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.model.type.TipoEstoque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "codigo"
        })
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String codigo;

    private LocalDateTime dataIni;

    private LocalDateTime dataAlt;

    private LocalDateTime dataFim;

    private Long vendedor;

    private String nome;

    private Double estoque;

    @Enumerated(EnumType.STRING)
    private TipoEstoque tipoEstoque;

    private String imagem;

    private Double preco;

    @ManyToMany(mappedBy = "produtoList", fetch = FetchType.LAZY)
    private List<Pedido> pedidoList;
}
