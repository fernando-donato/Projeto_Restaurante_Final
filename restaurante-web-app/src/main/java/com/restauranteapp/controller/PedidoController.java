package com.restauranteapp.controller;

import com.restauranteapp.model.Cliente;
import com.restauranteapp.model.ItemPedido;
import com.restauranteapp.model.Pedido;
import com.restauranteapp.model.Prato;
import com.restauranteapp.repository.ClienteRepository;
import com.restauranteapp.repository.PedidoRepository;
import com.restauranteapp.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Pedido> registrarPedido(@RequestBody PedidoDTO pedidoDTO) {

        Pedido novoPedido = new Pedido();
        novoPedido.setIdFuncionario(pedidoDTO.getIdFuncionario());
        novoPedido.setDataHora(LocalDateTime.now());
        novoPedido.setStatus("Aberto");

        // Associa cliente ID=1 como padrão
        Cliente clientePadrao = clienteRepository.findById(1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente padrão ID=1 não encontrado"));
        novoPedido.setCliente(clientePadrao);

        // Processa os itens do carrinho
        for (PedidoDTO.ItemPedidoDTO itemDTO : pedidoDTO.getItens()) {
            Prato prato = pratoRepository.findById(itemDTO.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato ID=" + itemDTO.getId() + " não encontrado"));

            ItemPedido novoItem = new ItemPedido();
            novoItem.setPrato(prato);
            novoItem.setQuantidade(itemDTO.getQty());
            novoItem.setPedido(novoPedido); // Link bidirecional

            novoPedido.getItens().add(novoItem);
        }

        // Salva Pedido E Itens (devido ao CascadeType.ALL)
        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

        return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
    }
}
