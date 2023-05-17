package com.pi.feiradigital.controller.system;

import com.pi.feiradigital.model.records.ClienteRecord;
import com.pi.feiradigital.model.records.VendedorRecord;
import com.pi.feiradigital.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendedor")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cadastro")
    public ResponseEntity<?> adicionar(@RequestBody VendedorRecord vendedorRecord){
        return vendedorService.adicionar(vendedorRecord);
    }
}
