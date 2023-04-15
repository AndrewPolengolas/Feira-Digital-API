package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.records.ClienteRecord;
import com.pi.feiradigital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> adicionar(@RequestBody ClienteRecord clienteRecord){
        return clienteService.adicionar(clienteRecord);
    }


}
