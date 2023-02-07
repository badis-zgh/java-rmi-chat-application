
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println(" • Server running successfully..");
        Registry registry = LocateRegistry.createRegistry(2222);
        registry.rebind("chatServer", new ImpStubs());
        System.out.println(" • Server is started!");
        System.out.println(" • ▼ All the infos is here ▼ ");
    }
}