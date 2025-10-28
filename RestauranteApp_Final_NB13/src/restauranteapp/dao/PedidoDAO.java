package restauranteapp.dao;
import restauranteapp.model.Pedido;
import restauranteapp.model.ItemPedido;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class PedidoDAO {
    public int salvarPedido(Pedido pedido) throws SQLException {
        String sqlPedido = "INSERT INTO pedidos (id_cliente, data_hora, status, id_funcionario) VALUES (?, ?, ?, ?)";
        String sqlItem = "INSERT INTO itens_pedidos (id_pedidos, id_pratos, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, pedido.getCliente().getId());
                LocalDateTime dt = pedido.getDataHora() != null ? pedido.getDataHora() : LocalDateTime.now();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                ps.setString(2, dt.format(fmt));
                ps.setString(3, pedido.getStatus() != null ? pedido.getStatus() : "Aberto");
                ps.setNull(4, Types.INTEGER);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) pedido.setId(rs.getInt(1)); }
            }
            try (PreparedStatement psItem = conn.prepareStatement(sqlItem)) {
                List<ItemPedido> itens = pedido.getItens();
                for (ItemPedido it : itens) {
                    psItem.setInt(1, pedido.getId());
                    psItem.setInt(2, it.getPrato().getId());
                    psItem.setInt(3, it.getQuantidade());
                    psItem.executeUpdate();
                }
            }
            conn.commit();
            return pedido.getId();
        }
    }
}
