package restauranteapp.view;
import javax.swing.*; import java.awt.*;
public class TelaFuncionarios extends JFrame {
    public TelaFuncionarios(){ init(); }
    private void init(){ setTitle("Funcionários"); setSize(600,400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); add(new JLabel("Tela de Funcionários (em construção)"), BorderLayout.CENTER); }
}
