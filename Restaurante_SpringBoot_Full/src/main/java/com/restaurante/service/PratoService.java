package com.restaurante.service;
import com.restaurante.model.Prato;
import com.restaurante.repository.PratoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PratoService {
    private final PratoRepository repo;
    public PratoService(PratoRepository repo){this.repo=repo;}
    public List<Prato> listar(){ return repo.findAll(); }
    public Prato buscar(Integer id){ return repo.findById(id).orElse(null); }
}
