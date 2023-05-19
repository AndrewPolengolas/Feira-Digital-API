package com.pi.feiradigital.model.records;

import com.pi.feiradigital.model.Produto;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public record PedidoRecord(List<Produto> produtos) {
    
    //List<Produto> produtos = produtosEntrada;

   
}
