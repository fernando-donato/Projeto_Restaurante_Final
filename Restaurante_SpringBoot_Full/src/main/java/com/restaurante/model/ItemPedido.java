
package com.restaurante.model;
import jakarta.persistence.*;
@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_itens_pedidos;
    @ManyToOne
    @JoinColumn(name = "id_pedidos")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_pratos")
    private Prato prato;
    private Integer quantidade;
    private Double precoUnitario;
    public Integer getId_itens_pedidos(){return id_itens_pedidos;} public void setId_itens_pedidos(Integer id){this.id_itens_pedidos=id;}
    public Pedido getPedido(){return pedido;} public void setPedido(Pedido pedido){this.pedido=pedido;}
    public Prato getPrato(){return prato;} public void setPrato(Prato prato){this.prato=prato;}
    public Integer getQuantidade(){return quantidade;} public void setQuantidade(Integer quantidade){this.quantidade=quantidade;}
    public Double getPrecoUnitario(){return precoUnitario;} public void setPrecoUnitario(Double precoUnitario){this.precoUnitario=precoUnitario;}
    public double getSubtotal(){ return (precoUnitario!=null?precoUnitario:0.0) * (quantidade!=null?quantidade:0); }
}
