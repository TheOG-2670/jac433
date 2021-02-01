import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Interface extends Remote
{
	Car registerPlate(Car car) throws RemoteException;
}
