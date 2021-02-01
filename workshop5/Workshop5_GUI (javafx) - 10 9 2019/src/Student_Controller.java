import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Student_Controller
{
    private Student student = new Student();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<String> courseList = new ArrayList<>();
    private Alert invalidInput;
    public TextField id_input = new TextField(),
                    first_name_input = new TextField(),
                    last_name_input = new TextField(),
                    course_code_input = new TextField();
    public Text course_add_indicate = new Text();
    public TextArea results = new TextArea();
    public ScrollPane scroll_results = new ScrollPane(results);

    public boolean validateStudent() throws InvalidParameterException, NumberFormatException
    {

        if (Integer.parseInt(id_input.getText()) > 0 ||
                first_name_input.getText().length() > 0 ||
                last_name_input.getText().length() > 0)
        {
            student.setID(Integer.parseInt(id_input.getText()));
            student.setFirstName(first_name_input.getText());
            student.setLastName(last_name_input.getText());

            return true;
        }
        else
        {
            return false;
        }
    }

    public void loadFile(ActionEvent actionEvent)
    {
        ArrayList<Student> storedStudents; //store any previously saved student objects from file
        results.clear(); //clear text are of any previous text
        try
        {
            storedStudents = ReadStudent.readStudentFromFile("data.txt"); //retrieve previous data stored in file


            if (new File("data.txt").length() > 0) //if the file is not empty
            {
                if (studentList.isEmpty())
                {
                    studentList.addAll(storedStudents); //copy over all stored student objects into current student arraylist
                }

                //print student arraylist information
                for (int i=0; i < storedStudents.size(); i++)
                {
                    results.appendText("Student " + (i+1) + ":\n" +
                                    "ID: " + storedStudents.get(i).getID() +
                                    "\nFirst Name: " + storedStudents.get(i).getFirstName() +
                                    "\nLast Name: " + storedStudents.get(i).getLastName()  +
                                    "\nCourses: ");

                    for (String course: storedStudents.get(i).getCoursesList())
                    {
                        results.appendText(course + " ");
                    }
                    //divider line to separate next student record being displayed
                    results.appendText("\n__________________________________________________\n\n");
                }


            }

        }
        catch(IOException | ClassNotFoundException error)
        {
            Alert readError = new Alert(Alert.AlertType.ERROR, "Error while reading file", ButtonType.CLOSE);
            readError.showAndWait();
            results.setText("File is empty!");
        }

    }

    public void addCourse(ActionEvent actionEvent)
    {
        try
        {
            if (validateStudent() && !course_code_input.getText().equals(""))
            {
                //gets text string from course code text field and adds it to the list
                courseList.add(course_code_input.getText());

                //clears course code text field after user pressed the '+' button
                course_code_input.clear();
                //displays added course message
                course_add_indicate.setText("Course Added!");

                //sets timer to clear added course message after 1 second from the user pressing the '+' button
                new Timer().schedule(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        course_add_indicate.setText("");
                    }
                }, 1000);
            }
            else
            {
                new Alert(Alert.AlertType.ERROR, "The course code must not be empty!", ButtonType.CLOSE).show();
            }
        }
        catch(InvalidParameterException e)
        {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
        catch(NumberFormatException error)
        {
            new Alert(Alert.AlertType.ERROR, "The ID must only contain numbers 0-9", ButtonType.CLOSE).show();
        }


    }

    public void saveStudentInfo(ActionEvent actionEvent)
    {

        try
        {
            if (validateStudent())
            {
                student.setCourseList(courseList);

                studentList.add(new Student(student.getID(), student.getFirstName(), student.getLastName(), student.getCoursesList()));
                WriteStudent.saveStudentToFile(studentList, "data.txt");

                courseList.clear();
                id_input.clear();
                first_name_input.clear();
                last_name_input.clear();
                course_code_input.clear();
                student.getCoursesList().clear();

                System.out.println("\nInformation successfully saved to file!");
            }

        }
        catch(InvalidParameterException | IOException e)
        {
            invalidInput = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            invalidInput.show();
        }
        catch (NumberFormatException e)
        {
            invalidInput = new Alert(Alert.AlertType.ERROR, "The ID must only contain numbers 0-9", ButtonType.CLOSE);
            invalidInput.show();
        }

    }

}
