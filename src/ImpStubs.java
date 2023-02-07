
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ImpStubs extends UnicastRemoteObject implements Face {

    List<String> users = new ArrayList<>();
    List<Message> messages = new ArrayList<>();

    public ImpStubs() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        messages.add(message);
    }

    @Override
    public boolean join(String username) throws RemoteException {
        boolean flag = false;

        for (String user : users) {

            if (username.equals(user)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }

        if (flag == false) {
            users.add(username);
            Message message = new Message();
            message.setMessage(" Joined the conversation.");
            message.setUsername(username);
            messages.add(message);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getAllUsers() throws RemoteException {

        for (String user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Override
    public List<Message> getAllMessage() throws RemoteException {

        for (Message msg : messages) {
            System.out.println(msg.getUsername() + " " + msg.getMessage());
        }
        return messages;
    }

}