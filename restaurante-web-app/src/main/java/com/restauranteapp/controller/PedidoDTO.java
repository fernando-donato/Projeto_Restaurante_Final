package com.restauranteapp.controller;

import lombok.Data;
import java.util.List;

// Esta classe representa o JSON que o front-end vai enviar
@Data
public class PedidoDTO {

    private Integer idFuncionario; 
    private List<ItemPedidoDTO> itens; 

    @Data
    public static class ItemPedidoDTO {
        private Integer id; // ID do Prato
        private int qty; // Quantidade
    }
}
