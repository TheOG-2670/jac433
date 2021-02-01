package Shapes;

//implicilty inherits Shape interface
public class Rectangle extends Square
{
	private double length;

	public Rectangle()
	{
		this.length = 0.0;
	}
	
	public Rectangle(double width, double length) throws RectangleException, SquareException
	{
		if (width > 0.0 && length > 0.0)
		{
			super.setWidth(width);
			this.length = length;
		}
		else
		{
			throw new RectangleException("Invalid side(s)");
		}

	}

	public void setLength(double length) throws RectangleException
	{
		if (length > 0.0)
		{
			this.length = length;
		}
		else
		{
			throw new RectangleException("Invalid side!");
		}

	}
	
	public double getLength()
	{
		return this.length;
	}

	public double getWidth()
	{
		return super.getWidth();
	}
	
	@Override
	public double calculatePerimeter()
	{
		return ( this.getWidth() + this.getLength()*2 );
	}

	public Area rectangleArea = () -> this.getLength()*this.getWidth();
	
	@Override //overrides Square class method
	public String toString()
	{
		return String.format("Rectangle {w=" + this.getWidth()
				+ ", h=" + this.getLength()
				+ "} perimeter = %.6f, area = %.6f", this.calculatePerimeter(), this.rectangleArea.calculateArea());
	}
	
}
