package Shapes;

//implicitly inherits Shape interface
public class Parallelogram extends Rectangle 
{
	
	public Parallelogram(double width, double length) throws RectangleException, SquareException
	{
		if (width > 0.0 && length > 0.0)
		{
			super.setWidth(width);
			super.setLength(length);
		}
		else
		{
			throw new RectangleException("Invalid side(s)!");
		}

	}

	public double getWidth()
	{
		return super.getWidth();
	}

	public double getLength()
	{
		return super.getLength();
	}
	
	@Override
	public double calculatePerimeter()
	{
		return ( (2*this.getLength()) + (2*this.getWidth()) );
	}
	
	@Override //overrides Rectangle class method
	public String toString()
	{
		return String.format("Parallelogram {w=" + super.getWidth()
							+ ", h=" + super.getLength()
							+ "} perimeter = %.6f", this.calculatePerimeter());
	}
}
