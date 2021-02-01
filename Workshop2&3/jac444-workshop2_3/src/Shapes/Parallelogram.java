package Shapes;

//implicitly inherits Shape interface
public class Parallelogram extends Rectangle 
{
	
	public Parallelogram(double width, double length) throws Exception
	{
		if (width > 0.0 && length > 0.0)
		{
			super.setWidth(width);
			super.setLength(length);
		}
		else
		{
			throw new Exception("Invalid side(s)!");
		}

	}	
	
	@Override
	public double calculatePerimeter()
	{
		return ( (2*super.getLength()) + (2*super.getWidth()) );
	}
	
	@Override //overrides Rectangle class method
	public String toString()
	{
		return String.format("Parallelogram {w=" + super.getWidth()
							+ ", h=" + super.getLength()
							+ "} perimeter = %.6f", this.calculatePerimeter());
	}
}
