package com.restaurante;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    @Test
    public void testCalcularTotal() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new ItemPedido(new Prato("Prato 1", 10.0), 2)); // 2 x 10
        pedido.adicionarItem(new ItemPedido(new Prato("Prato 2", 20.0), 1)); // 1 x 20

        PedidoService service = new PedidoService();
        double total = service.calcularTotal(pedido);

        assertEquals(40.0, total, 0.01, "O total do pedido deve ser 40.0");
    }
}
