package com.pi.feiradigital.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EnderecoRequest {
        private String cep;
        private String cidade;
        private String complemento;
        private LocalDateTime dataAlt;
        private LocalDateTime dataFim;
        private LocalDateTime dataIni;
        private String estado;
        private String logradouro;
        private long numero;
        private String pais;
        private long status;
        private String tipoEndereco;
        private long pedidoId;
        
        public void save(Endereco endereco) {
        }
    
    }
    