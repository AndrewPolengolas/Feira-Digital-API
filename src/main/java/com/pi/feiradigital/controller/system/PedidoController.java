package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.Pedido;
import com.pi.feiradigital.model.records.PedidoRecord;
import com.pi.feiradigital.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/adicionar")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRecord pedidoRecord) {
        return pedidoService.adicionar(pedidoRecord);
    }
}
