package com.pi.feiradigital.model.records;

import com.pi.feiradigital.model.Produto;

import java.util.List;

public record PedidoRecord(List<Produto> produtos) {
}
