package Shapes;

public class Triangle implements Shape
{
	
	private double side1=0.0, side2=0.0, side3=0.0;
	
	public Triangle(double side1, double side2, double side3) throws TriangleException
	{
		if ( (side1 > 0.0 && side2 > 0.0 && side3 > 0.0)
				&& (side1 + side2) > side3)
		{
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
		}
		else
		{
			throw new TriangleException("Invalid side(s)!");
		}
	}

	public double[] getSides()
	{
		return new double[] {this.side1, this.side2, this.side3};
	}
	
	@Override
	public double calculatePerimeter()
	{
		return getSides()[0] + getSides()[1] + getSides()[2];
	}
	
	@Override
	public String toString()
	{
		return String.format("Triangle {s1=" + this.getSides()[0]
				+ ", s2=" + this.getSides()[1] + ", s3=" +
				this.getSides()[2] + "}" + " perimeter = %.6f", this.calculatePerimeter());
	}

}
