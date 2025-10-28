package restauranteapp.controller;
import restauranteapp.dao.FuncionarioDAO;
import restauranteapp.model.Funcionario;
import java.sql.SQLException; import java.util.List;
public class FuncionarioController { private final FuncionarioDAO dao = new FuncionarioDAO();
    public List<Funcionario> listar() throws SQLException { return dao.listar(); }
}
