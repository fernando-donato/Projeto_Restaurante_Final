
package com.restaurante.model;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    @Column(nullable=false)
    private String nome;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
    public Integer getId_cliente(){return id_cliente;} public void setId_cliente(Integer id){this.id_cliente=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public String getTelefone(){return telefone;} public void setTelefone(String telefone){this.telefone=telefone;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public List<Pedido> getPedidos(){return pedidos;} public void setPedidos(List<Pedido> pedidos){this.pedidos=pedidos;}
}
