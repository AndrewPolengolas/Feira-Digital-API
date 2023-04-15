package com.pi.feiradigital.model.records;

import com.pi.feiradigital.model.type.Categoria;
import com.pi.feiradigital.model.type.TipoEstoque;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record ProdutoRecord(
        String nome,
        Double estoque,
        TipoEstoque tipoEstoque,
        String imagem,
        Double preco,
        Categoria categoria
) {
}
