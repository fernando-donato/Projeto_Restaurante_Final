package com.restauranteapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pratos")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pratos")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Lob 
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private String categoria; // Ex: "Entrada", "Prato Principal"
}
