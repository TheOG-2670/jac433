import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient
{
	private RMI_Interface rmiLookup = null;

	public static void main(String[] args)
	{
		System.out.println("RMI Client running!");
		RMIClient client = new RMIClient();
		
		//create new car object
		Car[] unregisteredCars = {
				new Car("Odyssey", "", 100000, null),
				new Car("Odyssey", "Silver", 100000, null),
				new Car("Civic", "Black", 33000, null),
				new Car("Model S", "Machine Grey", 0, null)
		};
		
		//print unregistered car object
		for (Car car: unregisteredCars)
		{
			
			System.out.println("\nUnregistered: " + car);
		
		
			//establish RMI service connection with server
			try {
				/*searches for rmi registry service name and port number on host machine (local machine),
				casts it to the rmi interface to store it in the client's rmi interface object to access remote
				object's methods*/
				client.rmiLookup = (RMI_Interface) Naming.lookup("rmi://localhost:7777/plateRegistration");
				System.out.println("Client: connection to RMI server successful!");
				
				//send car object to RMI server to register plate and return it
				Car currentCar = client.rmiLookup.registerPlate(car);
				
				//print car with registered plate number
				System.out.println("Registered: " + currentCar);
				
			//handle URL parsing, remote reference and rmi lookup/binding exceptions
			} catch (MalformedURLException e) {
				System.out.println("URL could not be parsed; details:");
				e.printStackTrace();
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
				//System.out.println("remote method could not be called; details:");
				//e.printStackTrace();
			} catch (NotBoundException e) {
				System.out.println("unable to lookup rmi service name on specified port and host; details:");
				e.printStackTrace();
			}
		}
	}
}
