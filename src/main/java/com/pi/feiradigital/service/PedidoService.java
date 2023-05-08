package com.pi.feiradigital.service;

import com.pi.feiradigital.helper.CodigoHelper;
import com.pi.feiradigital.helper.DataHelper;
import com.pi.feiradigital.helper.UserHelper;
import com.pi.feiradigital.model.Cliente;
import com.pi.feiradigital.model.Pedido;
import com.pi.feiradigital.model.Produto;
import com.pi.feiradigital.model.records.PedidoRecord;
import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.model.type.StatusPedido;
import com.pi.feiradigital.repository.ClienteRepository;
import com.pi.feiradigital.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private CodigoHelper codigoHelper;

    @Autowired
    private DataHelper dataHelper;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> adicionar(PedidoRecord pedidoRecord){

        Pedido pedido = new Pedido();

        pedido.setDataIni(dataHelper.getDataHora());
        pedido.setCodigo(codigoHelper.unique());
        pedido.setStatus(Status.ATIVO);

        Double total = 0.0;

        for (Produto produto : pedidoRecord.produtos()){
            total += produto.getValorTotal();
        }

        String vendedores = "";

        for (Produto produto : pedidoRecord.produtos()){
            vendedores = vendedores + "/" + produto.getVendedor();
        }

        Cliente cliente = clienteRepository.findByUsuario(userHelper.getUser().get().getId());

        pedido.setProdutoList(pedidoRecord.produtos());
        pedido.setStatusPedido(StatusPedido.EM_ANDAMENTO);
        pedido.setValorTotal(total);
        pedido.setCliente(cliente);
        pedido.setVendedores(vendedores);

        return new ResponseEntity<>(StatusPedido.EM_ANDAMENTO, HttpStatus.OK);
    }

}
