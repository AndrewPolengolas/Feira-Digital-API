package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.Produto;
import com.pi.feiradigital.model.records.ProdutoFiltradoRecord;
import com.pi.feiradigital.model.records.ProdutoRecord;
import com.pi.feiradigital.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/listar")
    public ResponseEntity<?> listarTodos(@RequestBody ProdutoFiltradoRecord produtoFiltradoRecord){
        return produtoService.listarTodos(produtoFiltradoRecord);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionar(@RequestBody ProdutoFiltradoRecord produtoFiltradoRecord){
        return produtoService.selecionar(produtoFiltradoRecord);
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
