package com.pi.feiradigital.service;

import com.pi.feiradigital.helper.CodigoHelper;
import com.pi.feiradigital.helper.DataHelper;
import com.pi.feiradigital.helper.UserHelper;
import com.pi.feiradigital.model.Produto;
import com.pi.feiradigital.model.records.ProdutoFiltradoRecord;
import com.pi.feiradigital.model.records.ProdutoRecord;
import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.model.type.TipoPreco;
import com.pi.feiradigital.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private CodigoHelper codigoHelper;

    @Autowired
    private DataHelper dataHelper;

    public ResponseEntity<?> selecionar(ProdutoFiltradoRecord filtro) {

        Produto produto = produtoRepository.findByStatusAndCodigo(
                Status.ATIVO,
                filtro.codigo()
        );

        if (produto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity<?> listarTodos(ProdutoFiltradoRecord filtro) {

        Integer pageNo = filtro.pagina() - 1;
        Integer pageSize = 20;
        String sortBy = "id";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Produto> produtosPaging = null;

        List<Produto> listaProdutos = null;

        Long total;

        switch (filtro.tipoPreco()) {
            case MENOR_PRECO:
                listaProdutos = produtoRepository.filtarProdutosPrecoAsc(
                        filtro.precoMin() != null ? filtro.precoMin() : null,
                        filtro.precoMax() != null ? filtro.precoMax() : null,
                        filtro.categoria() != null ? filtro.categoria() : null,
                        filtro.vendedor() != null ? filtro.vendedor() : null,
                        filtro.nome() != null ? filtro.nome() : null,
                        pageNo * pageSize,
                        pageSize
                );

                total = produtoRepository.totalFiltarProdutosPreco(
                        filtro.precoMin() != null ? filtro.precoMin() : null,
                        filtro.precoMax() != null ? filtro.precoMax() : null,
                        filtro.categoria() != null ? filtro.categoria() : null,
                        filtro.vendedor() != null ? filtro.vendedor() : null,
                        filtro.nome() != null ? filtro.nome() : null
                );

                produtosPaging = new PageImpl<>(listaProdutos, paging, total);
                break;
            case MAIOR_PRECO:
                listaProdutos = produtoRepository.filtarProdutosPrecoDesc(
                        filtro.precoMin() != null ? filtro.precoMin() : null,
                        filtro.precoMax() != null ? filtro.precoMax() : null,
                        filtro.categoria() != null ? filtro.categoria() : null,
                        filtro.vendedor() != null ? filtro.vendedor() : null,
                        filtro.nome() != null ? filtro.nome() : null,
                        pageNo * pageSize,
                        pageSize
                );

                total = produtoRepository.totalFiltarProdutosPreco(
                        filtro.precoMin() != null ? filtro.precoMin() : null,
                        filtro.precoMax() != null ? filtro.precoMax() : null,
                        filtro.categoria() != null ? filtro.categoria() : null,
                        filtro.vendedor() != null ? filtro.vendedor() : null,
                        filtro.nome() != null ? filtro.nome() : null
                );

                produtosPaging = new PageImpl<>(listaProdutos, paging, total);
                break;
        }

        return new ResponseEntity<>(listaProdutos, HttpStatus.OK);
    }

    public ResponseEntity<?> adicionar(ProdutoRecord produtoRecord) {

        Produto produto = new Produto();

        produto.setNome(produtoRecord.nome());
        produto.setEstoque(produtoRecord.estoque());
        produto.setImagem(produtoRecord.imagem());
        produto.setPreco(produtoRecord.preco());
        produto.setTipoEstoque(produtoRecord.tipoEstoque());
        produto.setCategoria(produtoRecord.categoria());

        produto.setDataIni(dataHelper.getDataHora());
        produto.setCodigo(codigoHelper.unique());
        produto.setStatus(Status.ATIVO);
        produto.setVendedor(userHelper.getUser().get().getId());

        Produto retorno = produtoRepository.save(produto);

        return new ResponseEntity<>(retorno, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editar(Produto produto) {

        Produto objProduto = produtoRepository.findByStatusAndCodigo(
                Status.ATIVO,
                produto.getCodigo()
        );

        if (objProduto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        objProduto.setNome(produto.getNome());
        objProduto.setEstoque(produto.getEstoque());
        objProduto.setImagem(produto.getImagem());
        objProduto.setPreco(produto.getPreco());
        objProduto.setTipoEstoque(produto.getTipoEstoque());
        objProduto.setCategoria(produto.getCategoria());

        objProduto.setDataAlt(dataHelper.getDataHora());

        Produto retorno = produtoRepository.save(produto);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    public ResponseEntity<?> excluir(Produto produto) {

        Produto objProduto = produtoRepository.findByStatusAndCodigo(
                Status.ATIVO,
                produto.getCodigo()
        );

        if (objProduto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        objProduto.setDataFim(dataHelper.getDataHora());
        objProduto.setStatus(Status.INATIVO);

        produtoRepository.save(produto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
