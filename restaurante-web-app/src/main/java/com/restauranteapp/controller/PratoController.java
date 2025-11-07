package com.restauranteapp.controller;

import com.restauranteapp.model.Prato;
import com.restauranteapp.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pratos")
public class PratoController {

    @Autowired
    private PratoRepository pratoRepository;

    @GetMapping
    public List<Prato> listarPratos() {
        return pratoRepository.findAll();
    }
}
