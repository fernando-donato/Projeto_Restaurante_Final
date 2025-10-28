package restauranteapp.view;
import javax.swing.*; import javax.swing.table.DefaultTableModel;
import restauranteapp.controller.ClienteController; import restauranteapp.model.Cliente;
import java.awt.*; import java.sql.SQLException;
public class TelaClientes extends JFrame {
    private final ClienteController controller = new ClienteController();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Nome","Telefone","Email"},0);
    private final JTable table = new JTable(model);
    public TelaClientes(){ init(); load(); }
    private void init(){ setTitle("Clientes"); setSize(700,400); setLocationRelativeTo(null); setLayout(new BorderLayout()); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel(); JButton btnAdd = new JButton("Adicionar"); JButton btnRefresh = new JButton("Atualizar"); JButton btnClose = new JButton("Fechar");
        btnAdd.addActionListener(e -> { JTextField n=new JTextField(); JTextField t=new JTextField(); JTextField em=new JTextField();
            Object[] fields = {"Nome:", n, "Telefone:", t, "Email:", em}; int ok = JOptionPane.showConfirmDialog(this, fields, "Novo Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (ok==JOptionPane.OK_OPTION) { try { controller.cadastrar(n.getText(), t.getText(), em.getText()); load(); } catch (SQLException ex) { JOptionPane.showMessageDialog(this, "Erro: "+ex.getMessage()); } } });
        btnRefresh.addActionListener(e -> load()); btnClose.addActionListener(e -> dispose());
        top.add(btnAdd); top.add(btnRefresh); top.add(btnClose);
        add(top, BorderLayout.NORTH); add(new JScrollPane(table), BorderLayout.CENTER);
    }
    private void load(){ model.setRowCount(0); try { for (Cliente c : controller.listar()) model.addRow(new Object[]{c.getId(), c.getNome(), c.getTelefone(), c.getEmail()}); } catch (SQLException ex) { JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage()); } }
}
