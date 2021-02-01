package Shapes;

//Shape interface describes a general shape (no reference to specific dimensions)
public interface Shape 
{

	double calculatePerimeter();

	/*REMOVE BEFORE NEXT LAB
	//each class must have their respective functions (ie: circle has radius, not width)
	/*public abstract void setWidth(double width);
	public abstract double getWidth();*/

	//only function inherited by all classes
	//'public abstract' implicit (not necessary to explicitly define)

	//automatically overrides from Object class
	//public abstract String toString();*/
}
