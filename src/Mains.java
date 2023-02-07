import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Mains {

    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry("localhost", 2222);
        Face chat = (Face) registry.lookup("chatServer");
        // Pass the reference to java GUI.
        new Login(chat);
    }
}
