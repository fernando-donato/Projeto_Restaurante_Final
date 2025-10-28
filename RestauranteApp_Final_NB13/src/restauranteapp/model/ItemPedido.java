package restauranteapp.model;
public class ItemPedido {
    private int id; private Prato prato; private int quantidade;
    public ItemPedido() {}
    public ItemPedido(Prato p, int q){this.prato=p; this.quantidade=q;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public Prato getPrato(){return prato;} public void setPrato(Prato p){this.prato=p;}
    public int getQuantidade(){return quantidade;} public void setQuantidade(int q){this.quantidade=q;}
}
