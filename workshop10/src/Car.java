import java.io.Serializable;

public class Car implements Serializable{
	
	private String model;
	private String color;
	private int mileage;
	private int plate;
	
	public Car()
	{
		this.color = null;
		this.mileage = 0;
		this.model = null;
		this.plate = 0;
	}
	
	public Car(String model, String color, int mileage, String plate)
	{
		if (!model.equals(null) && !color.equals(null) && mileage > 0)
		{
			this.model = model;
			this.color = color;
			this.mileage = mileage;
		}
	
	}
	
	public void setModel(String model)
	{
		if (!model.equals(null) || !model.equals(""))
		{
			this.model = model;
		}
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public void setColor(String color)
	{
		if (!color.equals(null) || !color.equals(""))
		{
			this.color = color;
		}
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public void setMileage(int mileage)
	{
		if (mileage > 0)
		{
			this.mileage = mileage;
		}
	}
	
	public int getMileage()
	{
		return this.mileage;
	}
	
	public void setPlate(int plate)
	{
		if (plate > 0)
		{
			this.plate = plate;
		}
	}
	
	public int getPlate()
	{
		return this.plate;
	}
	
	public boolean isEmpty()
	{
		if (this.getModel().equals("")
			|| this.getColor().equals("")
			|| this.getMileage() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public final String toString()
	{
		String carDetails = "Model: " + this.getModel() + ", Color: " + this.getColor() 
		+ ", Mileage: " + this.getMileage();
		
		if (this.getPlate() == 0)
		{
			return (carDetails + ", Plate: N/A");
		}
		else
		{
			return (carDetails + ", Plate: " + this.getPlate());
		}
		
	}
}
