package restauranteapp.view;
import javax.swing.*; import java.awt.*;
public class TelaLogin extends JFrame {
    public TelaLogin(){ init(); }
    private void init(){
        setTitle("Login - RestauranteApp"); setSize(420,220); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel center = new JPanel(new GridLayout(3,2,5,5));
        JLabel lblUser = new JLabel("Usuário:"); JTextField txtUser = new JTextField();
        JLabel lblPass = new JLabel("Senha:"); JPasswordField txtPass = new JPasswordField();
        center.add(lblUser); center.add(txtUser); center.add(lblPass); center.add(txtPass);
        JButton btnEntrar = new JButton("Entrar"); btnEntrar.addActionListener(e -> { new TelaMenu().setVisible(true); dispose(); });
        JPanel south = new JPanel(); south.add(btnEntrar);
        add(center, BorderLayout.CENTER); add(south, BorderLayout.SOUTH);
    }
}
