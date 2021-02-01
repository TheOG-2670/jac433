package Shapes;

//all sub-classes implicitly implement Shape interface
//Square class implements Shape interface and inherits its abstract methods
public class Square implements Shape
{
	//Square data fields
	private double width;

	//default constructor creates empty square
	public Square()
	{
		this.width = 0.0;
	}
	
	//overloaded constructor creates square with given parameters
	public Square(double width) throws SquareException
	{
		if (width > 0.0)
		{
			this.width = width;
		}
		else
		{
			throw new SquareException("Invalid side!");
		}
	}


	public void setWidth(double width) throws SquareException
	{
		if (width > 0.0)
		{
			this.width = width;
		}
		else
		{
			throw new SquareException("Invalid side!");
		}
	}


	public double getWidth() 
	{
		return this.width;
	}

	@Override
	public double calculatePerimeter() 
	{
		return this.getWidth()*4;
	}

	public Area squareArea = () -> Math.pow(this.getWidth(), 2);
	
	@Override //overrides Object class method
	public String toString()
	{
		return String.format("Square {s=" + this.width 
				+ "} perimeter = %.6f, area = %.6f", this.calculatePerimeter(), this.squareArea.calculateArea());
	}

}
