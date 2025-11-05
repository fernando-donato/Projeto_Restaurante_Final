package com.restaurante.controller;
import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service){ this.service = service; }
    @GetMapping public String listar(Model model){ model.addAttribute("clientes", service.listar()); return "clientes/list"; }
    @GetMapping("/novo") public String novo(Model model){ model.addAttribute("cliente", new Cliente()); return "clientes/form"; }
    @PostMapping("/salvar") public String salvar(@ModelAttribute Cliente cliente){ service.salvar(cliente); return "redirect:/clientes"; }
}
