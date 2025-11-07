package com.restauranteapp.controller;

import com.restauranteapp.model.Funcionario;
import com.restauranteapp.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario novoFuncionario) {
        novoFuncionario.setId(null);
        Funcionario funcionarioSalvo = funcionarioRepository.save(novoFuncionario);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/garcons")
    public List<Funcionario> listarGarcons() {
        return funcionarioRepository.findByCargo("Garçom");
    }
}
