import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login extends JFrame {

    private String username;
    Face chat;

    List<String> users = new ArrayList<>();

    private JPanel panel;
    private JLabel welcome;
    private JButton join;
    private JTextField input;

    // Initial my frame here..
    public Login(Face chat) throws RemoteException {

        frame();
        this.chat = chat;
        this.setTitle("Chats App");
        this.setSize(400, 130);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void frame() {

        panel = new JPanel();
        panel.setLayout(null);

        welcome = new JLabel(" Joins us and have the nice conversation.");
        join = new JButton("Join us");
        input = new JTextField();

        // Positions of components..
        welcome.setBounds(10, 5, 370, 30);
        input.setBounds(10, 40, 250, 30);
        join.setBounds(260, 40, 100, 30);

        // action button..
        join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doLogin(e);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(join);
        panel.add(input);
        panel.add(welcome);
        getContentPane().add(panel);
        pack();
    }

    private void doLogin(ActionEvent event) throws RemoteException {
        login();
    }

    private void login() throws RemoteException {

        username = input.getText();
        users = chat.getAllUsers();

        if (users.contains(username)) {
            JOptionPane.showMessageDialog(null, " Sorry you already exist, Please enter a new name.");
            System.exit(1);
        } else {
            new App(chat, username);
            this.setVisible(false);
        }
    }

}
