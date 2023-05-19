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
import com.pi.feiradigital.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pi.feiradigital.helper.DataHelper;
import com.pi.feiradigital.model.Endereco;
import com.pi.feiradigital.model.EnderecoRequest;
import com.pi.feiradigital.model.records.EnderecoRecord;
import com.pi.feiradigital.model.type.Status;
import com.pi.feiradigital.repository.EnderecoRepository;
import com.pi.feiradigital.model.EnderecoRequest;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private DataHelper dataHelper;

    public ResponseEntity<?> adicionar(EnderecoRequest enderecoRequest) {

        Endereco endereco = new Endereco();

        endereco.setCep(enderecoRequest.getCep());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setDataAlt(dataHelper.getDataHora());
        endereco.setDataFim(dataHelper.getDataHora());
        endereco.setDataIni(dataHelper.getDataHora());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setPais(endereco.getPais());
        endereco.setStatus(endereco.getStatus());
        endereco.setTipoEndereco(endereco.getTipoEndereco());
        endereco.setPedido_id(endereco.getPedido_id());


        enderecoRequest.save(endereco);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }
}
