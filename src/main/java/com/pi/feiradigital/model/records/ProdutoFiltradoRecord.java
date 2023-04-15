package com.pi.feiradigital.model.records;

import com.pi.feiradigital.model.type.TipoPreco;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.query.Param;

public record ProdutoFiltradoRecord(
        @NotBlank Integer pagina,
        @NotBlank TipoPreco tipoPreco,
        Double precoMin,
        Double precoMax,
        String categoria,
        String vendedor,
        String nome
) {
}
