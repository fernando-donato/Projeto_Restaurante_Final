package com.restaurante;

public class PedidoService {
    public double calcularTotal(Pedido pedido) {
        return pedido.getItens().stream()
            .mapToDouble(ItemPedido::getSubtotal)
            .sum();
    }
}
