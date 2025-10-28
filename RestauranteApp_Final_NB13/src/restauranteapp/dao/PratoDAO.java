package restauranteapp.dao;
import restauranteapp.model.Prato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PratoDAO {
    public List<Prato> listar() throws SQLException {
        List<Prato> lista = new ArrayList<>();
        String sql = "SELECT id_pratos, nome, preco FROM pratos";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Prato p = new Prato();
                p.setId(rs.getInt("id_pratos"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }
        }
        return lista;
    }
    public Prato buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_pratos, nome, preco FROM pratos WHERE id_pratos = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prato p = new Prato();
                    p.setId(rs.getInt("id_pratos"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));
                    return p;
                }
            }
        }
        return null;
    }
}
