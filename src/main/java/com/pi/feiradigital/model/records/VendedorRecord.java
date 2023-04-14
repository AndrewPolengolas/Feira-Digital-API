package com.pi.feiradigital.model.records;

import com.pi.feiradigital.model.type.TipoDocumento;
import com.pi.feiradigital.model.type.TipoPessoa;

public record VendedorRecord(
        String nome,

        String documento,

        TipoDocumento tipoDocumento,

        String celular,

        String email,

        TipoPessoa tipoPessoa,
        UserRecord userRecord
) {
}
