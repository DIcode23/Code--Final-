
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class User {
    String Nome;
    String Senha;
    String Email;
    String ContaDiferente;
    String NomeEmpresa, NomePessoal, SenhaPessoal, SenhaEmpresa;

    public static HashMap<String, String> userListE = new HashMap<String, String>();
    public static HashMap<String, String> userListP = new HashMap<String, String>();

    public User() {

    }

    public User(String Nome, String Senha, String Email, String ContaDiferente) {
        this.Nome = Nome;
        this.Senha = Senha;
        this.Email = Email;
        this.ContaDiferente = ContaDiferente;
    }

    @Override
    public String toString() {
        return Nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String NomeSET) {
        this.Nome = NomeSET;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContaDiferente() {
        return ContaDiferente;
    }

    public void setContaDiferente(String contaDiferente) {
        ContaDiferente = contaDiferente;
    }

    public void CreateUser() {
        JFrame frame = new JFrame("Cadastro no LIFEinvader");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel namelLabel = new JLabel("Username:");
        namelLabel.setBounds(20, 20, 100, 25);

        JTextField nameTextField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 100, 25);

        JLabel contLabel = new JLabel("Tipo de conta:");
        contLabel.setBounds(20, 80, 100, 25);

        JTextField tipoTextField = new JTextField(20);

        JPasswordField passwordField = new JPasswordField(20);

        JButton createButton = new JButton("Criar conta");
        createButton.setBounds(150, 100, 100, 30);

        createButton.addActionListener(e -> {
            Nome = nameTextField.getText();
            Senha = new String(passwordField.getPassword());
            ContaDiferente = tipoTextField.getText();

            if (ContaDiferente.equalsIgnoreCase("EMPRESA")) {
                CompanyUser userNewE = new CompanyUser(Nome, Email, Senha, ContaDiferente);
                userListE.put(Nome, Senha);
                frame.dispose();
                userNewE.HomeUser();
            } else if (ContaDiferente.equalsIgnoreCase("PESSOAL")) {
                PersonUser userNewP = new PersonUser(Nome, Email, Senha, ContaDiferente);
                userListP.put(Nome, Senha);
                frame.dispose();
                userNewP.HomeUser();
            }
        });

        frame.add(namelLabel);
        frame.add(nameTextField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(tipoTextField);
        frame.add(contLabel);
        frame.add(createButton);
        frame.setVisible(true);

    }

    public void HomeUser() {
        JFrame frame = new JFrame("Bem-Vindo ao LIFEinvader " + getNome());
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
        }
    }

    public static void DeleteUser(String nome) {
        int choise = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este perfil", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (choise == JOptionPane.YES_OPTION) {
            if (userListE.containsKey(nome) || userListP.containsKey(nome)) {
                userListE.remove(nome);
                userListP.remove(nome);
            }
            JOptionPane.showMessageDialog(null, "O usuário " + nome + " foi removido com sucesso");
        }
    }

    public static void ListUser() {
        JFrame frame = new JFrame("Lista de Usuários");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> userListModel = new DefaultListModel<>();
        JList<String> userList = new JList<>(userListModel);

        for (String name : userListE.keySet()) {
            userListModel.addElement(name);
        }
        for (String name : userListP.keySet()) {
            userListModel.addElement(name);
        }

        JScrollPane scrollPane = new JScrollPane(userList);

        frame.add(scrollPane);

        frame.setPreferredSize(new Dimension(300, 200));
        frame.pack();
        frame.setVisible(true);

    }

    public void UserLogin() {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel usernameLabel = new JLabel("UserName");
        JTextField usernameField = new JTextField(20);
        JLabel passwordJLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordJLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            Nome = usernameField.getText();
            Senha = new String(passwordField.getPassword());
            AuthUser();
            frame.dispose();
        });

        frame.pack();
        frame.setVisible(true);
    }

    public void AuthUser() {
        boolean auth = false;
        do {
            if (userListE.containsKey(Nome) && userListE.get(Nome).equals(Senha)) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                auth = true;
                CompanyUser user = new CompanyUser(Nome, Email, Senha, ContaDiferente);
                user.HomeUser();
            } else if (userListP.containsKey(Nome) && userListP.get(Nome).equals(Senha)) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                auth = true;
                PersonUser user = new PersonUser(Nome, Email, Senha, ContaDiferente);
                user.HomeUser();
            } else {
                JOptionPane.showMessageDialog(null, "Dados inválidos!");
                UserLogin();
                return;
            }
        } while (!auth);

    }

    public static HashMap<String, ArrayList<String>> friendList = new HashMap<>();

    public void addFriend() {
        JFrame frame = new JFrame("Adicionar Amigo");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JComboBox<String> userListComboBox = new JComboBox<>();
        JButton addButton = new JButton("Adicionar Amigo");
        addButton.setBounds(150, 100, 100, 30);

        for (String name : userListE.keySet()) {
            if (!name.equals(Nome) && !areFriends(name)) {
                userListComboBox.addItem(name);
            }
        }

        for (String name : userListP.keySet()) {
            if (!name.equals(Nome) && !areFriends(name)) {
                userListComboBox.addItem(name);
            }
        }

        userListComboBox.setPreferredSize(new Dimension(200, 30));

        frame.add(userListComboBox);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = (String) userListComboBox.getSelectedItem();
                if (friendName != null && userListE.containsKey(friendName) || userListP.containsKey(friendName)) {
                    if (!friendList.containsKey(Nome)) {
                        friendList.put(Nome, new ArrayList<>());
                    }
                    friendList.get(Nome).add(friendName);
                    JOptionPane.showMessageDialog(null, Nome + " e " + friendName + " agora são FRIENDSSS");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "ERRO: não foi possível adicionar a amizade.");
                    frame.dispose();
                }
                HomeUser();
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    public void ListFriends() {
        JFrame frame = new JFrame("Lista de Amigos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea friendTextArea = new JTextArea(10, 20);
        friendTextArea.setEditable(false);

        if (friendList.containsKey(Nome)) {
            friendTextArea.append("Sua lista de amigos, " + Nome + ":\n");
            ArrayList<String> friends = friendList.get(Nome);
            for (String friend : friends) {
                friendTextArea.append(friend + "\n");
                frame.dispose();
            }
            frame.dispose();
        } else {
            friendTextArea.append("Você não possui amigos");
            frame.dispose();
        }
        HomeUser();

        JScrollPane scrollPane = new JScrollPane(friendTextArea);

        frame.add(scrollPane);
        frame.setPreferredSize(new Dimension(300, 200));
        frame.pack();
        frame.setVisible(true);
    }

    public boolean areFriends(String friendName) {
        if (friendList.containsKey(Nome)) {
            ArrayList<String> friends = friendList.get(Nome);
            return friends.contains(friendName);
        }
        return false;
    }

    public void removeFriend() {
        if (friendList.containsKey(Nome)) {
            ArrayList<String> friends = friendList.get(Nome);

            JFrame frame = new JFrame("Remover Amigo");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Sua lista de amigos, " + Nome + ":");
            panel.add(label);

            JList<String> friendListGUI = new JList<>(friends.toArray(new String[0]));
            panel.add(new JScrollPane(friendListGUI));

            JLabel removeLabel = new JLabel("Digite o nome do amigo que deseja remover: ");
            panel.add(removeLabel);

            JTextField friendToRemoveField = new JTextField(20);
            panel.add(friendToRemoveField);

            JButton removeButton = new JButton("Remover Amigo");
            panel.add(removeButton);

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String friendName = friendToRemoveField.getText();
                    if (friends.contains(friendName)) {
                        friends.remove(friendName);
                        JOptionPane.showMessageDialog(frame, Nome + " e " + friendName + " não são mais amigos!");
                        frame.dispose();
                        HomeUser();
                    } else {
                        JOptionPane.showMessageDialog(frame, Nome + " não era amigo de " + friendName + ".");
                        HomeUser();
                    }
                }
            });

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem amigos ainda. Adicione amigos antes de listar.");
            HomeUser();
        }
    }

    public void updateUserData(String nome) {
        JFrame frame = new JFrame("Atualizar dados de usuário");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel nameLabel = new JLabel("Novo UserName:");
        JTextField nameField = new JTextField(20);

        JButton updateButton = new JButton("Atualizar Dados");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoNome = nameField.getText();

                if (userListE.containsKey(nome)) {
                    if (!novoNome.isEmpty()) {
                        setNome(novoNome);
                    }

                    userListE.get(nome);
                    userListE.get(Senha);
                    userListE.remove(nome);
                    userListE.remove(Senha);
                    userListE.put(novoNome, Senha);

                }

                frame.dispose();
                HomeUser();

            }
        });

        frame.pack();
        frame.setVisible(true);

    }

}