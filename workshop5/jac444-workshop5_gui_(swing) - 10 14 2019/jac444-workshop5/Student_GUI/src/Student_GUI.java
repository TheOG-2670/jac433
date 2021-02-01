import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Student_GUI
{
    private static JFrame frame = new JFrame("Workshop 5 - IO and Serialization"); //window and title

    private static Student student = new Student();
    private static ArrayList<String> courseList = new ArrayList<>(6);
    private static ArrayList<Student> studentList = new ArrayList<>();

    //ui component declarations
    private static JButton[] buttons = new JButton[3];
    private static JLabel[] labels = new JLabel[6];
    private static JTextField[] textFields = new JTextField[4];
    private static JTextArea results = new JTextArea( 8, 33);
    private static JScrollPane scrollPane = new JScrollPane(results);
    private static JPanel panel = new JPanel();
    private static SpringLayout sl = new SpringLayout();

    //checks if input information is valid, saves it and returns true for valid data
    public static boolean validStudent() throws NumberFormatException, InvalidParameterException
    {
        if ( Integer.parseInt(textFields[0].getText()) > 0
            || textFields[1].getText().length() > 0
            || textFields[2].getText().length() > 0 )
        {
            //textFields[0] -> student ID
            Student_GUI.student.setID( Integer.parseInt(textFields[0].getText()) ) ;
            //textFields[1] -> first name
            Student_GUI.student.setFirstName(textFields[1].getText());
            //textFields[2] -> last name
            Student_GUI.student.setLastName(textFields[2].getText());

            return true;
        }
        else
        {
            return false;
        }
    }

    //configures event-handling for the "save", "add course" and "load" buttons
    public static void setUIEvents() throws NumberFormatException, InvalidParameterException
    {
        //Load button - deserializes objects from file
        buttons[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<Student> storedData = new ArrayList<>();
                try
                {
                    //load any stored data into ArrayList
                    storedData = ReadStudent.readStudentFromFile("data.txt");
                }
                catch (IOException | ClassNotFoundException error)
                {
                    JOptionPane.showMessageDialog(frame, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }


                if (new File("data.txt").length() > 0)
                {
                    //clear text area of any previous text
                    results.setText("");

                    //load studentList ArrayList with any objects already stored in the file
                    if (Student_GUI.studentList.isEmpty())
                    {
                        Student_GUI.studentList.addAll(storedData);
                    }

                    //print student objects to text area
                    for (int i=0; i < Student_GUI.studentList.size(); i++)
                    {
                        results.append("Student " + (i+1) + "\n" +
                                Student_GUI.studentList.get(i).toString() + "\nCourses: ");

                        for (String course: Student_GUI.studentList.get(i).getCoursesList())
                        {
                            results.append(course + " ");
                        }

                        results.append("\n\n");
                    }
                }
                else
                {
                    results.setText("File is empty!");
                }

            }
        });

        //Add Course button - adds entered course to student's course list
        buttons[2].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    if (validStudent() && textFields[3].getText().length() > 0)
                    {
                        //textFields[3] -> course code input field
                        Student_GUI.courseList.add(textFields[3].getText());

                        if (Student_GUI.courseList.size() == 1)
                        {
                            //labels[4] -> added course indicator
                            labels[4].setText(Student_GUI.courseList.size() + " course added");
                        }
                        else
                        {
                            labels[4].setText(Student_GUI.courseList.size() + " courses added");
                        }

                        //clear text field to allow entry for next course code
                        textFields[3].setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Course code must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //display error dialog window if any student info is invalid (including empty course list)
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(frame, "Please re-enter your ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(InvalidParameterException e)
                {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        //Save button - serializes student object to file
        buttons[0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    //validate student second time before serializing to file
                    if (validStudent() && courseList.size() > 0)
                    {
                        //save course list
                        Student_GUI.student.setCourseList(Student_GUI.courseList);

                        //create new student object and add to ArrayList
                        Student_GUI.studentList.add(new Student(Student_GUI.student.getID(),
                                Student_GUI.student.getFirstName(), Student_GUI.student.getLastName(),
                                Student_GUI.student.getCoursesList()));

                        //serialize ArrayList of student objects to file
                        WriteStudent.saveStudentToFile(studentList, "data.txt");
                        System.out.println("\nInformation successfully saved to file!");

                        //clear all input fields, course list for current student and added course
                        //indicator message after student is successfully serialized to file
                        Student_GUI.student.getCoursesList().clear();
                        Student_GUI.courseList.clear();
                        textFields[0].setText("");
                        textFields[1].setText("");
                        textFields[2].setText("");
                        textFields[3].setText("");
                        labels[4].setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one course code", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //show pop-up error message dialog window if exception is thrown from invalid student info
                catch(IOException | InvalidParameterException error)
                {
                    JOptionPane.showMessageDialog(frame, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(frame, "Please re-enter your ID", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    //creates ui components and positions them within the chosen layout manager (SpringLayout)
    public static void constructUI()
    {
        panel.setLayout(sl); //add layout manager for panel

        //create and add buttons to panel
        String[] buttonName = {"Save", "Load", "+"};
        for (int i=0; i < buttonName.length; i++)
        {
            buttons[i] = new JButton(buttonName[i]);
            panel.add(buttons[i]);
        }

        //create and add labels to panel
        String[] labelName = {"Student ID:", "First Name:", "Last Name:", "Course Code:", "", "File Contents:"};
        for (int i=0; i < labelName.length; i++)
        {
            labels[i] = new JLabel(labelName[i]);
            panel.add(labels[i]);
        }

        //create and add text fields to panel
        for (int i=0; i < textFields.length; i++)
        {
            textFields[i] = new JTextField(10);
            panel.add(textFields[i]);
        }

        //position all ui components

        //position "student id" label
        sl.putConstraint(SpringLayout.WEST, labels[0], 55, SpringLayout.WEST, panel);
        //position id input text field
        sl.putConstraint(SpringLayout.WEST, textFields[0], 130, SpringLayout.WEST, panel);

        //position "first name" label
        sl.putConstraint(SpringLayout.SOUTH, labels[1], 50, SpringLayout.NORTH, labels[0]);
        sl.putConstraint(SpringLayout.WEST, labels[1], 50, SpringLayout.WEST, panel);

        //position first name input text field
        sl.putConstraint(SpringLayout.SOUTH, textFields[1], 51, SpringLayout.NORTH, labels[0]);
        sl.putConstraint(SpringLayout.EAST, textFields[1], 117, SpringLayout.EAST, labels[1]);

        //position "last name" label
        sl.putConstraint(SpringLayout.SOUTH, labels[2], 50, SpringLayout.NORTH, labels[1]);
        sl.putConstraint(SpringLayout.WEST, labels[2], 50, SpringLayout.WEST, panel);

        //position last name input text field
        sl.putConstraint(SpringLayout.SOUTH, textFields[2], 51, SpringLayout.NORTH, labels[1]);
        sl.putConstraint(SpringLayout.EAST, textFields[2], 118, SpringLayout.EAST, labels[2]);

        //position "course code" label
        sl.putConstraint(SpringLayout.SOUTH, labels[3], 50, SpringLayout.NORTH, labels[2]);
        sl.putConstraint(SpringLayout.WEST, labels[3], 37, SpringLayout.WEST, panel);

        //position course code text field
        sl.putConstraint(SpringLayout.SOUTH, textFields[3], 51, SpringLayout.NORTH, labels[2]);
        sl.putConstraint(SpringLayout.EAST, textFields[3], 118, SpringLayout.EAST, labels[3]);

        //position "file contents" label
        sl.putConstraint(SpringLayout.SOUTH, labels[5], 110, SpringLayout.NORTH, labels[3]);
        sl.putConstraint(SpringLayout.WEST, labels[5], 10, SpringLayout.WEST, panel);

        //position "Save" Button
        sl.putConstraint(SpringLayout.SOUTH, buttons[0], 70, SpringLayout.NORTH, labels[3]);
        sl.putConstraint(SpringLayout.EAST, buttons[0], 93, SpringLayout.EAST, labels[3]);

        //position "load" button
        sl.putConstraint(SpringLayout.SOUTH, buttons[1], 110, SpringLayout.NORTH, labels[3]);
        sl.putConstraint(SpringLayout.WEST, buttons[1], 147, SpringLayout.WEST, panel);

        //position "+" (add course) button
        sl.putConstraint(SpringLayout.SOUTH, buttons[2], 53, SpringLayout.NORTH, labels[2]);
        sl.putConstraint(SpringLayout.EAST, buttons[2], 50, SpringLayout.EAST, textFields[3]);

        //position scrollpane (with text area)
        panel.add(scrollPane);
        sl.putConstraint(SpringLayout.SOUTH, scrollPane, 205, SpringLayout.NORTH, buttons[0]);
        sl.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, panel);
        //never display horizontal scroll bar in text area
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //position course added indicator message
        sl.putConstraint(SpringLayout.SOUTH, labels[4], 33, SpringLayout.NORTH, labels[3]);
        sl.putConstraint(SpringLayout.EAST, labels[4], 130, SpringLayout.NORTH, textFields[3]);

    }

    public static void main(String[] args)
    {
        constructUI(); //create UI components and construct layout
        setUIEvents(); //configure events triggered by UI components

        //apply native system theme to ui components----------------------------------------------
        try
        {
            //set appearance of ui components to the native system
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {

        }
        //-----------------------------------------------------------------------------------------------

        results.setEditable(false); //sets results text field to read-only
        frame.add(panel); //add panel to frame
        frame.setSize(370,400); //size of window
        frame.setResizable(false); //prevents window from being resized
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when user presses 'X'
        frame.setVisible(true); //display GUI
    }
}
