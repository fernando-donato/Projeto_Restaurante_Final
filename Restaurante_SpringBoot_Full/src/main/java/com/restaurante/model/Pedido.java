
package com.restaurante.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedidos;
    private LocalDateTime data_hora = LocalDateTime.now();
    private String status = "Aberto";
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();
    private Double total = 0.0;
    public Integer getId_pedidos(){return id_pedidos;} public void setId_pedidos(Integer id){this.id_pedidos=id;}
    public LocalDateTime getData_hora(){return data_hora;} public void setData_hora(LocalDateTime d){this.data_hora=d;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public Cliente getCliente(){return cliente;} public void setCliente(Cliente c){this.cliente=c;}
    public List<ItemPedido> getItens(){return itens;} public void setItens(List<ItemPedido> itens){ this.itens=itens; recalcTotal(); }
    public void addItem(ItemPedido item){ item.setPedido(this); this.itens.add(item); recalcTotal(); }
    public Double getTotal(){ return total; }
    public void recalcTotal(){ this.total = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum(); }
}
