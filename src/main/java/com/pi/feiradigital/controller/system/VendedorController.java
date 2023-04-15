package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.records.ClienteRecord;
import com.pi.feiradigital.model.records.VendedorRecord;
import com.pi.feiradigital.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> adicionar(@RequestBody VendedorRecord vendedorRecord){
        return vendedorService.adicionar(vendedorRecord);
    }
}
