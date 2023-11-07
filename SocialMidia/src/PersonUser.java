
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonUser extends User {
    int idade;

    public PersonUser() {

    }

    public PersonUser(String Nome, String Email, String Senha, String ContaDiferente) {
        super(Nome, Senha, Email, ContaDiferente);

    }

    public void HomeUser() {
        JFrame frame = new JFrame("Bem-Vindo ao LIFEinvader " + getNome() + " Idade pessoal: " + idade);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(8, 1));

        buttonPanel.add(new JButton("Deletar seu Perfil"));
        buttonPanel.add(new JButton("Listar todos os usuários"));
        buttonPanel.add(new JButton("Criar um novo usuário"));
        buttonPanel.add(new JButton("Adicionar amizade"));
        buttonPanel.add(new JButton("Listar suas amizades"));
        buttonPanel.add(new JButton("Aba Login"));
        buttonPanel.add(new JButton("Remover Amizades"));
        buttonPanel.add(new JButton("Atualizar dados"));
        buttonPanel.add(new JButton("Inserir idade"));

        frame.add(buttonPanel, BorderLayout.CENTER);

        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ButtonClick(button.getText());
                        frame.dispose();
                    }
                });
            }
        }
        frame.pack();
        frame.setVisible(true);

    }

    public void ButtonClick(String buttonText) {
        if (buttonText.equals("Deletar seu Perfil")) {
            DeleteUser(getNome());
            CreateUser();
        } else if (buttonText.equals("Listar todos os usuários")) {
            ListUser();
            HomeUser();

        } else if (buttonText.equals("Criar um novo usuário")) {
            CreateUser();
        } else if (buttonText.equals("Adicionar amizade")) {
            addFriend();
        } else if (buttonText.equals("Listar suas amizades")) {
            ListFriends();
        } else if (buttonText.equals("Aba Login")) {
            UserLogin();
        } else if (buttonText.equals("Remover Amizades")) {
            removeFriend();
        } else if (buttonText.equals("Atualizar dados")) {
            updateUserData(getNome());
        } else if (buttonText.equals("Inserir idade")) {
            MostrarIdade();
        }
    }

    public void MostrarIdade() {
        JFrame frame = new JFrame("Mostrar Idade");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel ageLabel = new JLabel("Idade:");
        JTextField ageField = new JTextField(20);

        JButton showButton = new JButton("Mostrar Idade");

        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(new JLabel());
        panel.add(showButton);

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idadeTexto = ageField.getText();

                try {
                    idade = Integer.parseInt(idadeTexto);
                    JOptionPane.showMessageDialog(frame, "Sua idade é: " + idade);
                    frame.dispose();
                    HomeUser();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida. Digite um número inteiro para a idade.");
                }
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
