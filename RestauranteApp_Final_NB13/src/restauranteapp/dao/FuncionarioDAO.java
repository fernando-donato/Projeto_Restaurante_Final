package restauranteapp.dao;
import restauranteapp.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FuncionarioDAO {
    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT id_funcionarios, nome, cargo FROM funcionarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id_funcionarios"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                lista.add(f);
            }
        }
        return lista;
    }
}
