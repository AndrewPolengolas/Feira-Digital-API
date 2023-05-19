package com.pi.feiradigital.controller.system;

import java.io.Console;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi.feiradigital.model.EnderecoRequest;
import com.pi.feiradigital.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/enderecoapi")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/adicionar")
    //@PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<?> adicionar(@RequestBody EnderecoRequest enderecoRequest) {
        return enderecoService.adicionar(enderecoRequest);
    }
    
}