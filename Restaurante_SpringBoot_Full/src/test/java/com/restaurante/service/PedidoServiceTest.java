package com.restaurante.service;
import com.restaurante.model.ItemPedido;
import com.restaurante.model.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PedidoServiceTest {
    @Test
    public void testRecalcTotal() {
        Pedido p = new Pedido();
        ItemPedido i1 = new ItemPedido(); i1.setPrecoUnitario(10.0); i1.setQuantidade(1);
        ItemPedido i2 = new ItemPedido(); i2.setPrecoUnitario(20.0); i2.setQuantidade(2);
        p.addItem(i1); p.addItem(i2);
        p.recalcTotal();
        assertEquals(50.0, p.getTotal(), 0.001);
    }
}
