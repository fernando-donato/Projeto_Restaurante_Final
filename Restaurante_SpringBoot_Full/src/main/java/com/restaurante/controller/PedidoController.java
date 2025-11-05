package com.restaurante.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.model.ItemPedido;
import com.restaurante.model.Pedido;
import com.restaurante.model.Prato;
import com.restaurante.service.ClienteService;
import com.restaurante.service.PedidoService;
import com.restaurante.service.PratoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final PratoService pratoService;
    private final ObjectMapper mapper = new ObjectMapper();
    public PedidoController(PedidoService pedidoService, ClienteService clienteService, PratoService pratoService){
        this.pedidoService = pedidoService; this.clienteService = clienteService; this.pratoService = pratoService;
    }
    @GetMapping public String listar(Model model){ model.addAttribute("pedidos", pedidoService.listar()); return "pedidos/list"; }
    @GetMapping("/novo") public String novo(Model model){
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("pratos", pratoService.listar());
        return "pedidos/form";
    }
    @PostMapping("/salvar") public String salvar(@RequestParam Integer clienteId, @RequestParam String itemsJson){
        try {
            List<Map<String,Object>> items = mapper.readValue(itemsJson, new TypeReference<List<Map<String,Object>>>(){});
            Pedido pedido = new Pedido();
            pedido.setCliente(clienteService.buscar(clienteId));
            for (Map<String,Object> m : items){
                Integer pratoId = ((Number)m.get("pratoId")).intValue();
                Integer quantidade = ((Number)m.get("quantidade")).intValue();
                Double preco = ((Number)m.get("preco")).doubleValue();
                Prato prato = new Prato(); prato.setId_pratos(pratoId);
                ItemPedido it = new ItemPedido();
                it.setPrato(prato); it.setQuantidade(quantidade); it.setPrecoUnitario(preco);
                pedido.addItem(it);
            }
            pedidoService.salvarPedido(pedido);
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/pedidos?error";
        }
        return "redirect:/pedidos";
    }
}
