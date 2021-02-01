import java.security.cert.CollectionCertStoreParameters;
import java.text.Format;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

//*NOTE: CANNOT USE ANY CONTROL STATEMENTS (ie: if/else, for, while, do/while, switch/case
public class StudentProcess
{
    //create array of Student objects
    private static Student[] students = {
            new Student("Jack", "Smith", 50.0, "IT"),
            new Student("Aaron", "Johnson", 76.0, "IT"),
            new Student("Maaria", "White", 35.8, "Business"),
            new Student("John", "White", 47.0, "Media"),
            new Student("Laney", "White", 62.0, "IT"),
            new Student("Jack", "Jones", 32.9, "Business"),
            new Student("Wesley", "Jones", 42.89, "Media")};

    //LAB 7------------------------------------------------------------------------------------------------------------
    //TASK 1 ------------------------------------------------------------------------------------------------
        //create List out of Student object array
        private static List<Student> studentList = Arrays.asList(students);

        public static void displayStudentList()
        {
            //print list
            studentList.stream().forEach(System.out::println);
        }

    //-------------------------------------------------------------------------------------------------------

    //TASK 2 ------------------------------------------------------------------------------------------------


        public static void displayStudentGradeRange()
        {
            //create new stream
            Stream<Student> studentStream = studentList.stream();
            //print all students with grade of 50 or higher using customer Comparator anonymous class
            studentStream.filter(student -> student.getGrade() >= 50.0 || student.getGrade() <= 100).sorted(Comparator.comparing(Student::getGrade)).forEach(System.out::println);

        }

    //-------------------------------------------------------------------------------------------------------

    //TASK 3 ------------------------------------------------------------------------------------------------

        public static void displaySingleStudentGrade()
        {
            Stream<Student> studentStream = studentList.stream();
            //find first student with grade of 50 or higher and display
            studentStream.findFirst().filter(student -> student.getGrade() >= 50 || student.getGrade() <= 100).ifPresent(System.out::println);
        }

    //-------------------------------------------------------------------------------------------------------

    //TASK 4 ------------------------------------------------------------------------------------------------

        public static void displayStudentsByNameOrder()
        {

            Stream<Student> studentStreamAscending = studentList.stream();
            //display all students in ascending order by last names and then first names
            studentStreamAscending.sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName)).forEach(System.out::println);

            System.out.println("\nStudents in descending order by last name then first:");
            Stream<Student> studentStreamDescending = studentList.stream();
            //display students in descending order by last names and then first names
            studentStreamDescending.sorted( Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).reversed() ).forEach(System.out::println);
        }

    //-------------------------------------------------------------------------------------------------------

    //TASK 5 ------------------------------------------------------------------------------------------------

        public static void displayUniqueLastNames()
        {
            Stream<Student> studentStream = studentList.stream();
            //map each student to their last name and display distinct last names
            Stream<String> uniqueLastNameStream = studentStream.map(Student::getLastName).distinct().sorted();
            uniqueLastNameStream.forEach(System.out::println);
        }

    //-------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------

    //LAB 8 ----------------------------------------------------------------------------------------------------------
    //TASK 6-------------------------------------------------------------------------------------------------
        public static void displayFullNames()
        {
            //sort each student in order by last name and then first name
            Stream<Student> studentStream = studentList.stream().sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));
            //map each student's first name to their respective last name
            Stream<String> fullNameStream = studentStream.map(student -> student.getFirstName() + " " + student.getLastName());
            //print all student full names
            fullNameStream.forEach(System.out::println);
        }
    //-------------------------------------------------------------------------------------------------------

    //TASK 7-------------------------------------------------------------------------------------------------
        public static void displayStudentsByDepartment()
        {
            Stream<Student> studentStream = studentList.stream();
            Map<String, List<Student>> studentDept = studentStream.collect(Collectors.groupingBy(Student::getDepartment));

            System.out.println("Media");
            studentDept.get("Media").forEach((key)-> System.out.println("\t" + key));

            System.out.println("IT");
            studentDept.get("IT").forEach((key)-> System.out.println("\t" + key));

            System.out.println("Business");
            studentDept.get("Business").forEach((key)-> System.out.println("\t" + key));
        }
    //-------------------------------------------------------------------------------------------------------

    //TASK 8-------------------------------------------------------------------------------------------------
        public static void studentCountByDepartment()
        {
            Stream<Student> studentStream = studentList.stream();
            Map<String, Long> studentCount = studentStream.collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));

            System.out.println("Business has " + studentCount.get("Business") + " Student(s)");
            System.out.println("IT has " + studentCount.get("IT") + " Student(s)");
            System.out.println("Media has " + studentCount.get("Media") + " Student(s)");
        }
    //-------------------------------------------------------------------------------------------------------

    //TASK 9-------------------------------------------------------------------------------------------------
        public static void totalStudentGradeSum()
        {
            Stream<Student> studentStream = studentList.stream();
            DoubleStream totalGradeSumStream = studentStream.mapToDouble(Student::getGrade);

            System.out.println(totalGradeSumStream.sum());
        }
    //-------------------------------------------------------------------------------------------------------

    //TASK 10------------------------------------------------------------------------------------------------
        public static void totalStudentGradeAverage()
        {
            Stream<Student> studentStream = studentList.stream();
            DoubleStream totalGradeSumStream = studentStream.mapToDouble(Student::getGrade);

            System.out.format("%.2f", totalGradeSumStream.average().getAsDouble());
        }
    //-------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args)
    {
        System.out.println("Task 1:\n");
        displayStudentList();

        System.out.println();
        System.out.println("Task 2: \n\nStudents who got 50.0-100.0 sorted by grade:");
        displayStudentGradeRange();

        System.out.println();
        System.out.println("Task 3: \n\nFirst student who got 50.0-100.0:");
        displaySingleStudentGrade();

        System.out.println("\nTask 4: \n\nStudents in ascending order by last name then first:");
        displayStudentsByNameOrder();

        System.out.println("\nTask 5: \n\nUnique Student last names:");
        displayUniqueLastNames();

        System.out.println("\nTask 6: \n\nStudent names in order by last name then first name:");
        displayFullNames();

        System.out.println("\nTask 7: \n\nStudents by department:");
        displayStudentsByDepartment();

        System.out.println("\nTask 8: \n\nCount of Students by department:");
        studentCountByDepartment();

        System.out.print("\nTask 9: \n\nSum of Students' grades: ");
        totalStudentGradeSum();

        System.out.print("\nTask 10: \n\nAverage of Students' grades: ");
        totalStudentGradeAverage();
    }
}
