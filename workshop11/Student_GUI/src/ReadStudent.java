
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ReadStudent
{

    public static void printStudentInfo(ArrayList<Student> deserializedStudent)
    {
        for (int i=0; i < deserializedStudent.size(); i++)
        {
            if (!deserializedStudent.isEmpty())
            {
                System.out.println("Student " + (i+1) + ": ");

                System.out.println(deserializedStudent.get(i));

                System.out.print("Courses: ");
                for (String s: deserializedStudent.get(i).getCoursesList())
                {
                    System.out.print(s + " ");
                }
            }

            System.out.println();
            System.out.println();
        }
    }

    public static ArrayList<Student> readStudentFromFile(String filename) throws IOException, ClassNotFoundException
    {

    	
    	Connection connect = null;
    	Statement statement = null;
    	ArrayList<Student> studentList = new ArrayList<>();
    	
    	try
    	{
    		//establish connection to local database
    		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_test", "root", "11OGolan8991");
    		System.out.println("connection successfully established");
    		
    		//initialize statement object to execute CRUD operations
    		statement = connect.createStatement();
    		
    		Student s = null;
			ArrayList<String> courses = null;
			
			ResultSet r = statement.executeQuery("select * from Student;");
    		while(r.next())
    		{
    			s = new Student();
    			courses = new ArrayList<>();
    			s.setID(r.getInt("id"));
    			s.setFirstName(r.getString("first_name"));
    			s.setLastName(r.getString("last_name"));
    			
    			studentList.add(s);
    		}
    		r.close();
    		
    		ResultSet r1 = statement.executeQuery("select * from Course;");
    		while(r1.next())
			{
				
				if (r1.getInt("id") == s.getID())
				{
					courses.add(r1.getString("code"));
				}
				
			}
			s.setCourseList(courses);
    		r1.close();
    		
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		try {
    			if(statement != null && connect != null)
    			{
    				
    				statement.close();
    				connect.close();
    				
    			}
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	return studentList;
    }

    public static void main(String[] args)
    {
        try
        {
            //printStudentInfo( readStudentFromFile(args[0]) );
        	readStudentFromFile(null);
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("Error while reading file: " + e.getMessage());
        }

    }
}
