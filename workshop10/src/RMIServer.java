import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer 
{
	
	public static void main(String[] args)
	{
		System.out.println("RMI Server Running!");
		
		//create RMI socket connection for clients and bind RMI service with interface
		try {
			//inside try/catch block due to RemoteException
			RMI_Interface rmi = new RMI_Interface_Implement();
			
			//creates an rmi registry reference for a remote object on a specified port
			Registry registry = LocateRegistry.createRegistry(7777);
			//replaces previous rmi service name (if any) with specified name and binds it to the remote  object reference
			registry.rebind("plateRegistration", rmi);
			System.out.println("Server: RMI registry creation and binding successful!");
			
			
		} catch (RemoteException e) {
			System.out.println("Problem: " + e);
			System.exit(1);
		}
	}
}
