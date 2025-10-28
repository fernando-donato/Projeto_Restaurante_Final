package restauranteapp.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Pedido {
    private int id; private Cliente cliente; private LocalDateTime dataHora; private String status;
    private List<ItemPedido> itens = new ArrayList<>();
    public Pedido() {}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public Cliente getCliente(){return cliente;} public void setCliente(Cliente c){this.cliente=c;}
    public LocalDateTime getDataHora(){return dataHora;} public void setDataHora(LocalDateTime d){this.dataHora=d;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public List<ItemPedido> getItens(){return itens;} public void addItem(ItemPedido it){this.itens.add(it);}
}
