import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class WriteStudent
{
    public static void saveStudentToFile(ArrayList<Student> students, String filename ) throws IOException
    {
    	
    	Connection connect = null;
    	Statement statement = null;
    	
    	try
    	{
    		//establish connection to local database
    		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_test", "root", "11OGolan8991");
    		System.out.println("connection successfully established");
    		
    		//initialize statement object to execute CRUD operations
    		statement = connect.createStatement();
    		
    		
    		Student student = students.get(students.size()-1);
    		
    		statement.executeUpdate("insert into Student values("+student.getID()+
    				", \""+student.getFirstName()+"\", \""+student.getLastName()+"\");");
    		
    		for (String course: student.getCoursesList())
    		{
    			statement.executeUpdate("insert into Course values("+student.getID()+", \""+course+"\")");
    		}
    		
    		System.out.println("Student and Course information successfully inserted!");
    		
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try {
    			if(statement != null)
    			{
    				statement.close();
    				System.out.println("statement object closed!");
    			}
    			if (connect != null)
    			{
    				connect.close();
    				System.out.println("connection object closed!");
    			}
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    }

    public static Student getStudentInfo(Scanner[] studentInfoScanners, ArrayList<String> courses, int studentCount)
    {
        int studentID = 0, courseCount = 0;
        String firstName = null, lastName = null;

        System.out.println("\nInformation for student " + studentCount);

        System.out.print("Enter your student ID: ");
        studentID = studentInfoScanners[0].nextInt();

        System.out.print("Enter your first name: ");
        firstName = studentInfoScanners[1].nextLine();

        System.out.print("Enter your last name: ");
        lastName = studentInfoScanners[2].nextLine();


        System.out.print("Enter the number of courses you have this semester: ");
        courseCount = studentInfoScanners[3].nextInt();

         for (int i = 0; i < courseCount; i++)
         {
             System.out.print("Enter course " + (i + 1) + ": ");
             courses.add(studentInfoScanners[4].nextLine());
         }

        return new Student(studentID, firstName, lastName, courses);
    }

    public static void main(String[] args)
    {
        ArrayList<String> courses = new ArrayList<>();
        ArrayList<Student> serializedStudents = new ArrayList<>();
        boolean validInput = false;
        int numOfStudents = 0;

        while(!validInput)
        {
            try
            {
                //initialize scanners to get input from console
                Scanner[] studentInfoScanners = new Scanner[5];
                for (int i = 0; i < studentInfoScanners.length; i++)
                {
                    studentInfoScanners[i] = new Scanner(System.in);
                }

                System.out.print("Enter number of students: ");
                numOfStudents = studentInfoScanners[0].nextInt();

                //initialize array of student objects
                serializedStudents.ensureCapacity(numOfStudents);
                for (int studentCount = 0; studentCount < numOfStudents; studentCount++)
                {
                    //clear previous student's course list to make ready for next student (if any)
                    courses.clear();
                    //create and save student information into utilities.Student array
                    serializedStudents.add(getStudentInfo(studentInfoScanners, courses, studentCount + 1));
                }

                //break loop to save student info to file
                validInput = true;
                //serialize student array to file and pass filename (defined by args[0])
                saveStudentToFile(serializedStudents, args[0]);
                System.out.println("\nInformation successfully saved to file!");

                //check if scanners are not null and close them
                for (int i = 0; i < studentInfoScanners.length; i++)
                {
                    if (studentInfoScanners[i] != null)
                    {
                        studentInfoScanners[i].close();
                    }
                }

            }
            catch (InputMismatchException e)
            {
                //report any invalid type input
                System.out.println("Invalid input!\n");
            }
            catch (InvalidParameterException e)
            {
                //report any missing or invalid fields (from student constructor)
                System.out.println(e.getMessage());
            }
            catch (IOException e)
            {
                //report any errors during serialization operation
                System.out.println("Something went wrong during write operation! Details below: ");
                e.printStackTrace();
            }
        }

    }

}
