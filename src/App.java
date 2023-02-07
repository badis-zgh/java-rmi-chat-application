import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {

    List<String> users = new ArrayList<>();
    List<Message> messages = new ArrayList<>();

    Face chat;

    private String username;

    private JLabel name, description;
    private JPanel panel;
    private JTextPane chats;
    private JTextField input;
    private JButton send;
    private JScrollPane scroll;

    // Initial frame of chats clients..
    public App(Face chat, String username) throws RemoteException {
        frame();
        this.chat = chat;
        this.username = username;
        chat.join(username);
        execute();
    }

    public void frame() {

        panel = new JPanel();
        panel.setLayout(null);
        scroll = new JScrollPane();

        name = new JLabel();

        chats = new JTextPane();

        scroll.setViewportView(chats);

        description = new JLabel(" Send your message..");
        input = new JTextField();
        send = new JButton("Send");

        // html JTextPane
        chats.setEditable(false);
        chats.setContentType("text/html");
        chats.setText(
                "<html>\n <head>\n\n <body>\n\n <p style=\"margin-top: 0\">\n </p>\n </body>\n</head>\n</html>\n");

        // Positions of components
        name.setBounds(10, 10, 350, 30);
        scroll.setBounds(10, 40, 380, 500);
        description.setBounds(10, 540, 350, 30);
        input.setBounds(10, 570, 250, 30);
        send.setBounds(260, 570, 100, 30);
        // action here
        send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    doSend(e);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(description);
        panel.add(send);
        panel.add(scroll);
        panel.add(input);
        panel.add(name);

        getContentPane().add(panel);
        pack();
    }

    private void execute() {

        setTitle("Chats");
        setSize(400, 640);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        name.setText(" Welcome Mr." + username + " to the conversation.");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {
                    try {
                        refresh();
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void refresh() throws RemoteException {
        displayChat();
    }

    private void displayChat() throws RemoteException {

        chats.setText("");
        messages = chat.getAllMessage();
        String container = "<html><body><table>";

        for (Message msg : messages) {

            container += "<tr><td>"
                    + "</td><td><font color='rgb(255,0,0)'><b>"
                    + msg.getUsername()
                    + "</b> </font> </td><td>"
                    + msg.getMessage()
                    + "</td></tr>";

        }

        container += "</table></body></html>";
        chats.setText(container);
    }

    private void doSend(ActionEvent event) throws RemoteException {
        sendMSG();
    }

    private void sendMSG() throws RemoteException {

        String value = input.getText();
        input.setText("");

        Message message = new Message();
        message.setMessage(value);
        message.setUsername(username);

        chat.sendMessage(message);
    }
}