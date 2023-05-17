package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.records.ClienteRecord;
import com.pi.feiradigital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastro")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> adicionar(@RequestBody ClienteRecord clienteRecord){
        return clienteService.adicionar(clienteRecord);
    }


}
