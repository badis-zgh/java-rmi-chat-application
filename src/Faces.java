import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Faces extends Remote {

    public void sendMessage(Message message) throws RemoteException;

    public boolean join(String username) throws RemoteException;

    List<String> getAllUsers() throws RemoteException;

    List<Message> getAllMessage() throws RemoteException;
}