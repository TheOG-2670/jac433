import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Shapes.*;

public class Main 
{
	private Shape[] shape = null;
	private int currentShape = 0; //current shape number in Shape[] array

	public boolean validateShape(String[] token)
	{
		if (token.length < 3)
		{
			if (token[0].equals("Circle") || token[0].equals("Square"))
			{
				return true;
			}
		}
		else if (token.length < 4)
		{
			if (token[0].equals("Rectangle") || token[0].equals("Parallelogram"))
			{
				return true;
			}
		}
		else if (token[0].equals("Triangle") && (Double.parseDouble(token[1]) + Double.parseDouble(token[2])) > Double.parseDouble(token[3]))
		{
			return true;
		}
		return false;
	}

	//prints number of shapes created and each shape's information
	public final void printShape()
	{
		for (int i = 0; i < shape.length; i++)
		{
			if (shape[i] != null)
			{
				System.out.println(shape[i]); //implicitly calls toString() method
				System.out.println();
			}

		}
		
	}

	public void calculateTotalPerimeter()
	{
		double totalParallelogramPerimeter = 0.0,
				totalTrianglePerimeter = 0.0;

		for (int i=0; i < shape.length; i++)
		{
			if (shape[i] != null)
			{
				if (shape[i].getClass().getName().contains("Parallelogram") )
				{
					totalParallelogramPerimeter += shape[i].calculatePerimeter();
				}
				else if (shape[i].getClass().getName().contains("Triangle") )
				{
					totalTrianglePerimeter += shape[i].calculatePerimeter();
				}
			}

		}

		System.out.println("Total perimeter of Parallelogram is: "+ totalParallelogramPerimeter);
		System.out.println("Total perimeter of Triangle is: "+ totalTrianglePerimeter);
	}

	public void removeShape()
	{
		Shape minShape = null,
				maxShape = null;

		int i=0;
		//gets smallest triangle(s) and largest circle(s) from array
		while(i < shape.length)
		{
			if ( shape[i] instanceof Triangle )
			{
				if (minShape == null)
				{
					minShape = shape[i];
				}

				else if (minShape.calculatePerimeter() > shape[i].calculatePerimeter())
				{
					minShape = shape[i];
				}
			}
			else if ( shape[i] instanceof Circle )
			{
				if (maxShape == null)
				{
					maxShape = shape[i];
				}

				else if (maxShape.calculatePerimeter() < shape[i].calculatePerimeter())
				{
					maxShape = shape[i];
				}
			}
			i++;
		}

		//remove smallest triangle(s) and largest circle(s) from array
		i=0;
		while (i < shape.length)
		{
			if ( (shape[i] instanceof Triangle &&
					minShape.calculatePerimeter() == shape[i].calculatePerimeter())
					|| (shape[i] instanceof Circle &&
					maxShape.calculatePerimeter() == shape[i].calculatePerimeter()))
			{
				shape[i] = null;
			}
			i++;
		}
	}

	
	//construct valid shapes
	public void constructShape(String[] shapeTokens)
	{
		//shape array counter
		if (currentShape < shape.length )
		{
			try
			{
				if (shapeTokens.length < 3)
				{
					if (shapeTokens[0].equals("Square"))
					{
						shape[currentShape++] = new Square(Double.parseDouble(shapeTokens[1]));

					}
					else if ( shapeTokens[0].equals("Circle") )
					{
						shape[currentShape++] = new Circle(Double.parseDouble(shapeTokens[1]));

					}
				}
				else if (shapeTokens.length < 4)
				{
					if ( shapeTokens[0].equals("Rectangle") )
					{
						shape[currentShape++] = new Rectangle(Double.parseDouble(shapeTokens[1]),
								Double.parseDouble(shapeTokens[2]));

					}
					else if ( shapeTokens[0].equals("Parallelogram") )
					{
						shape[currentShape++] = new Parallelogram(Double.parseDouble(shapeTokens[1]),
								Double.parseDouble(shapeTokens[2]));
					}
				}
				else if ( shapeTokens[0].equals("Triangle") )
				{
					shape[currentShape++] = new Triangle( Double.parseDouble(shapeTokens[1]),
														 Double.parseDouble(shapeTokens[2]),
															Double.parseDouble(shapeTokens[3]));
				}

			}
			catch (CircleException error)
			{
				System.out.println(error.getMessage());
				currentShape--;
			}
			catch (SquareException error)
			{
				System.out.println(error.getMessage());
				currentShape--;
			}
			catch (TriangleException error)
			{
				System.out.println(error.getMessage());
				currentShape--;
			}
			catch(RectangleException error)
			{
				System.out.println(error.getMessage());
				currentShape--;
			}
		}
		
	}
	
	public void readFile(String fileName)
	{
		int shapeCount = 0;
		/*create new bufferedreader object to read characters from text file
		using anonymous filereader object*/
		for (int i=0; i < 2; i++)
		{
			if (shapeCount > 0)
			{
				shape = new Shape[shapeCount];
			}

			try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
			{
				//create buffer string to hold read characters
				String buffer;


				//continuously read from file until a null/empty line is encountered
				while ((buffer = br.readLine()) != null)
				{
					String[] token = buffer.split(",");

					if (i == 0 && !buffer.contains("-") && validateShape(token))
					{
						shapeCount++;
					}
					else if (i == 1)
					{
						constructShape(token);
					}
				}
			}
			//if there are any error with reading the file, catch the error as an
			//exception and print it
			catch (IOException error)
			{
				System.out.println(error.getMessage());
			}
		}

	}
	
	public static void main(String[] args)
	{
		//TASK 1-----------------------------------------------------
		Main main = new Main();
		System.out.println("------->JAC 444 Assignment 1<-------");
		System.out.println("------->Task 1 ... <-------");

		main.readFile(args[0]);
		System.out.println();

		System.out.println(main.shape.length + " shapes were created:");
		System.out.println();

		main.printShape();
		System.out.println();

		//TASK 2-----------------------------------------------------
		System.out.println("------->Task 2 ... <-------");
		main.removeShape();
		main.printShape();
		System.out.println();

		//TASK 3-----------------------------------------------------
		System.out.println("------->Task 3 ... <-------");
		main.calculateTotalPerimeter();
	}

}
