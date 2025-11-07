package com.restauranteapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itens_pedidos")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedidos", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pratos", nullable = false)
    private Prato prato;

    @Column(nullable = false)
    private int quantidade;
}
