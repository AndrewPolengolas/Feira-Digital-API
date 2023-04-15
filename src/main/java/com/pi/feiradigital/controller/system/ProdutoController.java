package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.Produto;
import com.pi.feiradigital.model.records.ProdutoFiltradoRecord;
import com.pi.feiradigital.model.records.ProdutoRecord;
import com.pi.feiradigital.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/listar")
    public ResponseEntity<?> listarTodos(@RequestBody ProdutoFiltradoRecord produtoFiltradoRecord){
        return produtoService.listarTodos(produtoFiltradoRecord);
    }

    @PostMapping("/adicionar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public ResponseEntity<?> adicionar(@Valid @RequestBody ProdutoRecord produtoRecord){
        return produtoService.adicionar(produtoRecord);
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public ResponseEntity<?> editar(@RequestBody Produto produto){
        return produtoService.editar(produto);
    }

    @PostMapping("/excluir")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public ResponseEntity<?> excluir(@RequestBody Produto produto){
        return produtoService.excluir(produto);
    }

}
