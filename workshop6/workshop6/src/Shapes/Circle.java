package Shapes;
import java.lang.Math;

public class Circle implements Shape
{
	private double radius=0.0;
	
	public Circle(double radius) throws CircleException
	{
		if ( radius > 0.0 )
		{
			this.radius = radius;
		}
		else
		{
			throw new CircleException("Invalid radius!");
		}
	}

	public void setRadius(double radius)
	{
		this.radius = radius;
	}

	public double getRadius()
	{
		return this.radius;
	}
	
	@Override //implements Shape interface's method
	public double calculatePerimeter()
	{
		return ((2*Math.PI)*this.radius);
	}

	public Area circleArea = () -> Math.PI*(Math.pow(this.getRadius(), 2));

	@Override //overrides Object's version of toString method
	public String toString()
	{
		return String.format("Circle {r=" + this.radius 
				+ "} perimeter = %.6f, area = %.6f", this.calculatePerimeter(), this.circleArea.calculateArea());
	}
}