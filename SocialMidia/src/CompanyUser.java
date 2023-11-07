
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CompanyUser extends User {
    String ramo;
    String anuncio = " ";

    public CompanyUser() {

    }

    public CompanyUser(String Nome, String Email, String Senha, String ContaDiferente) {
        super(Nome, Senha, Email, ContaDiferente);

    }

    public void HomeUser() {
        JFrame frame = new JFrame("Bem-Vindo ao LIFEinvader Companhia " + getNome() + ": " + anuncio);
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
        buttonPanel.add(new JButton("Incluir seu ramo e seu anuncio"));

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
        } else if (buttonText.equals("Incluir seu ramo e seu anuncio")) {
            Advertising();
        }
    }

    public void Advertising() {
        JFrame frame = new JFrame("Incluir anuncio e ramo");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel namelLabel = new JLabel("Ramo: ");
        namelLabel.setBounds(20, 20, 100, 25);

        JTextField nameTextField = new JTextField(20);

        JLabel AnuncioField = new JLabel("Anuncio:");
        AnuncioField.setBounds(20, 50, 100, 25);

        JTextField Field = new JTextField(20);

        JButton addButton = new JButton("Incluir em seu perfil.");
        addButton.setBounds(150, 100, 100, 30);

        addButton.addActionListener(e -> {
            ramo = nameTextField.getText();
            anuncio = Field.getText();

            frame.dispose();
            HomeUser();
        });

        frame.add(namelLabel);
        frame.add(nameTextField);
        frame.add(AnuncioField);
        frame.add(Field);
        frame.add(addButton);
        frame.setVisible(true);
    }

}
