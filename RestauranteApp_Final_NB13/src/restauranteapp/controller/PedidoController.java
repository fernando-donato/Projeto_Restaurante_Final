package restauranteapp.controller;
import restauranteapp.dao.PedidoDAO;
import restauranteapp.model.Pedido;
import java.sql.SQLException;
public class PedidoController { private final PedidoDAO dao = new PedidoDAO();
    public int registrarPedido(Pedido p) throws SQLException { return dao.salvarPedido(p); }
}
