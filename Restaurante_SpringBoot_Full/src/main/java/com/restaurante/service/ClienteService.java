package com.restaurante.service;
import com.restaurante.model.Cliente;
import com.restaurante.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteService {
    private final ClienteRepository repo;
    public ClienteService(ClienteRepository repo) { this.repo = repo; }
    public Cliente salvar(Cliente c) { return repo.save(c); }
    public List<Cliente> listar() { return repo.findAll(); }
    public Cliente buscar(Integer id){ return repo.findById(id).orElse(null); }
}
