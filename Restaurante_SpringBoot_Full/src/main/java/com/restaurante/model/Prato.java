
package com.restaurante.model;
import jakarta.persistence.*;
@Entity
@Table(name = "pratos")
public class Prato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pratos;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    public Integer getId_pratos(){return id_pratos;} public void setId_pratos(Integer id){this.id_pratos=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public String getDescricao(){return descricao;} public void setDescricao(String descricao){this.descricao=descricao;}
    public Double getPreco(){return preco;} public void setPreco(Double preco){this.preco=preco;}
    public String getCategoria(){return categoria;} public void setCategoria(String categoria){this.categoria=categoria;}
}
