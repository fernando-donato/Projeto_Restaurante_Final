package com.restauranteapp.controller;

import com.restauranteapp.model.Cliente;
import com.restauranteapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente novoCliente) {
        novoCliente.setId(null); 
        Cliente clienteSalvo = clienteRepository.save(novoCliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }
}
