package com.restaurante.service;
import com.restaurante.model.ItemPedido;
import com.restaurante.model.Pedido;
import com.restaurante.model.Prato;
import com.restaurante.repository.ItemPedidoRepository;
import com.restaurante.repository.PedidoRepository;
import com.restaurante.repository.PratoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepo;
    private final PratoRepository pratoRepo;
    private final ItemPedidoRepository itemRepo;
    public PedidoService(PedidoRepository pedidoRepo, PratoRepository pratoRepo, ItemPedidoRepository itemRepo){
        this.pedidoRepo = pedidoRepo; this.pratoRepo = pratoRepo; this.itemRepo = itemRepo;
    }
    @Transactional
    public Pedido salvarPedido(Pedido pedido){
        if(pedido.getItens()==null || pedido.getItens().isEmpty()){
            throw new IllegalArgumentException("Pedido deve conter ao menos um item");
        }
        for (ItemPedido it : pedido.getItens()){
            if (it.getPrato() != null && it.getPrato().getId_pratos() != null){
                Prato p = pratoRepo.findById(it.getPrato().getId_pratos()).orElse(null);
                it.setPrato(p);
                it.setPrecoUnitario(p != null ? p.getPreco() : it.getPrecoUnitario());
                it.setPedido(pedido);
            }
        }
        pedido.recalcTotal();
        Pedido saved = pedidoRepo.save(pedido);
        return saved;
    }
    public List<Pedido> listar(){ return pedidoRepo.findAll(); }
    public Pedido buscar(Integer id){ return pedidoRepo.findById(id).orElse(null); }
}
