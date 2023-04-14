package com.pi.feiradigital.controller.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping("/testando")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> teste(){
        return new ResponseEntity<>("Funcionou", HttpStatus.OK);
    }
}
