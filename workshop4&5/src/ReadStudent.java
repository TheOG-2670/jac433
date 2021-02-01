import java.io.*;

public class ReadStudent
{

    public static void printStudentInfo(Student[] deserializedStudent)
    {
        for (int i=0; i < deserializedStudent.length; i++)
        {
            if (!deserializedStudent[i].isEmpty())
            {
                System.out.println("Student " + (i+1) + ": ");

                System.out.println(deserializedStudent[i]);

                System.out.print("Courses: ");
                for (String s: deserializedStudent[i].getCoursesList())
                {
                    System.out.print(s + " ");
                }
            }

            System.out.println();
            System.out.println();
        }
    }

    public static void readStudentFromFile(String filename) throws IOException, ClassNotFoundException
    {

        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Student[] deserializedStudent = (Student[])objIn.readObject();
        printStudentInfo(deserializedStudent);

        fileIn.close();
        objIn.close();

    }

    public static void main(String[] args)
    {
        try
        {
            readStudentFromFile(args[0]);
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("Error while reading file: " + e.getMessage());
        }

    }
}
