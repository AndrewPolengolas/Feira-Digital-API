 package com.pi.feiradigital.model;

 import com.pi.feiradigital.model.type.Status;
 import com.pi.feiradigital.model.type.TipoEndereco;
 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;

 import java.time.LocalDateTime;

 @Entity
 @Table(name = "endereco")
 @Getter
 @Setter
 @AllArgsConstructor
 @NoArgsConstructor
 public class Endereco {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String cep;

     private String cidade;

     private String complemento;

     private LocalDateTime dataAlt;

     private LocalDateTime dataFim;

     private LocalDateTime dataIni;

     private String estado;

     private String logradouro;

     private Long numero;

     private String pais;

     private int status;

     private String tipoEndereco;

     private Long pedido_id;


 }
