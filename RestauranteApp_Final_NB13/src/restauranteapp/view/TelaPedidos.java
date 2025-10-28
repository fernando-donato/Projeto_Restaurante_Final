package restauranteapp.view;
import javax.swing.*; import java.awt.*;
public class TelaPedidos extends JFrame {
    public TelaPedidos(){ init(); }
    private void init(){ setTitle("Pedidos"); setSize(700,500); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); add(new JLabel("Tela de Pedidos (em construção)"), BorderLayout.CENTER); }
}
