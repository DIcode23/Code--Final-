import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LIFEinvader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

    
        JButton createUserButton = new JButton("Criar Usuário");
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        User U1 = new User();
                        U1.CreateUser();
                        frame.dispose();
                    }
                });
            }
        });

        JButton homeUserButton = new JButton("Login de usuário");
        homeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User currentUser = new User();
                currentUser.UserLogin();
            }
        });

        panel.add(createUserButton);
        panel.add(homeUserButton);

        frame.add(panel);

        frame.setVisible(true);
    }
}
