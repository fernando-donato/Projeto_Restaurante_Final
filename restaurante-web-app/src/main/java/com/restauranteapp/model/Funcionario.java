package com.restauranteapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionarios")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 14)
    private String cpf;

    @Column(nullable = false)
    private String cargo; // Ex: "Garçom", "Cozinheiro", "Gerente"

    @Column(name = "horario_trabalho", nullable = false)
    private String horarioTrabalho; // Ex: "Manhã (08:00-12:00)"

    @Column(precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;
}
