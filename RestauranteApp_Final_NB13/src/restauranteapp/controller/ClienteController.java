package restauranteapp.controller;
import restauranteapp.dao.ClienteDAO;
import restauranteapp.model.Cliente;
import java.sql.SQLException;
import java.util.List;
public class ClienteController {
    private final ClienteDAO dao = new ClienteDAO();
    public void cadastrar(String nome, String telefone, String email) throws SQLException {
        Cliente c = new Cliente(nome, telefone, email);
        dao.salvar(c);
    }
    public List<Cliente> listar() throws SQLException { return dao.listar(); }
}
