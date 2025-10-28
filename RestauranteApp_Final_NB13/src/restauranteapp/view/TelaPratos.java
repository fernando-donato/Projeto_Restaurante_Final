package restauranteapp.view;
import javax.swing.*; import java.awt.*;
public class TelaPratos extends JFrame {
    public TelaPratos(){ init(); }
    private void init(){ setTitle("Pratos"); setSize(600,400); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); add(new JLabel("Tela de Pratos (em construção)"), BorderLayout.CENTER); }
}
