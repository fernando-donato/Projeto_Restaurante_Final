package restauranteapp.controller;
import restauranteapp.dao.PratoDAO;
import restauranteapp.model.Prato;
import java.sql.SQLException; import java.util.List;
public class PratoController { private final PratoDAO dao = new PratoDAO();
    public List<Prato> listar() throws SQLException { return dao.listar(); }
    public Prato buscarPorId(int id) throws SQLException { return dao.buscarPorId(id); }
}
