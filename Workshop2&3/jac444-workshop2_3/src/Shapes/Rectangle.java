package Shapes;

//implicilty inherits Shape interface
public class Rectangle extends Square
{
	private double length;
	
	public Rectangle()
	{
		this.length = 0.0;
	}
	
	public Rectangle(double width, double length) throws Exception
	{
		if (width > 0.0 && length > 0.0)
		{
			this.setWidth(width);
			this.length = length;
		}
		else
		{
			throw new Exception("Invalid side(s)");
		}

	}

	public void setLength(double length)
	{
		this.length = length;
	}
	
	public double getLength()
	{
		return this.length;
	}
	
	@Override
	public double calculatePerimeter()
	{
		return ( (super.calculatePerimeter()/2) + this.length*2 );
	}
	
	@Override //overrides Square class method
	public String toString()
	{
		return String.format("Rectangle {w=" + super.getWidth()
				+ ", h=" + this.length
				+ "} perimeter = %.6f", this.calculatePerimeter());
	}
	
}
