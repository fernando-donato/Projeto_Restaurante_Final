package restauranteapp.view;
import javax.swing.*; import java.awt.*;
public class TelaMenu extends JFrame {
    public TelaMenu(){ init(); }
    private void init(){ setTitle("Menu Principal - RestauranteApp"); setSize(600,400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(new FlowLayout());
        JButton bClientes = new JButton("Clientes"); bClientes.addActionListener(e -> new TelaClientes().setVisible(true));
        JButton bFuncionarios = new JButton("Funcionários"); bFuncionarios.addActionListener(e -> new TelaFuncionarios().setVisible(true));
        JButton bPratos = new JButton("Pratos"); bPratos.addActionListener(e -> new TelaPratos().setVisible(true));
        JButton bPedidos = new JButton("Pedidos"); bPedidos.addActionListener(e -> new TelaPedidos().setVisible(true));
        JButton bSair = new JButton("Sair"); bSair.addActionListener(e -> System.exit(0));
        add(bClientes); add(bFuncionarios); add(bPratos); add(bPedidos); add(bSair);
    }
}
