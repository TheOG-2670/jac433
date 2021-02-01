import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject exports remote object to the rmi server and receives stub
//that is used to communicate with the remote object from the client/local machine
public class RMI_Interface_Implement extends UnicastRemoteObject implements RMI_Interface
{

	public RMI_Interface_Implement() throws RemoteException 
	{
		super();
	}

	@Override
	public Car registerPlate(Car car) throws RemoteException 
	{
		if (!car.isEmpty() && car.getPlate() == 0)
		{
			car.setPlate(car.hashCode());
		}
		else if (!car.isEmpty() && car.getPlate() > 0)
		{
			throw new RemoteException("Server: Unable to register car! Car already registered!");
			//System.out.println("Server: Unable to register car! Car already registered!");
		}
		else if (car.isEmpty() && car.getPlate() == 0)
		{
			throw new RemoteException("Server: Unable to register car! Insufficient car details!");
			//System.out.println("Server: Unable to register car! Insufficient car details!");
		}
		
		return car;
	}


}
