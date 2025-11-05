package com.restaurante.controller;
import com.restaurante.service.PratoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PratoController {
    private final PratoService service;
    public PratoController(PratoService service){ this.service = service; }
    @GetMapping("/pratos") public String listar(Model model){ model.addAttribute("pratos", service.listar()); return "pratos/list"; }
}
