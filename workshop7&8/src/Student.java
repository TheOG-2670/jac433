import java.util.Formatter;

public class Student
{
    private String firstName = null;
    private String lastName = null;
    private String department = null;
    private double grade = 0.0;

    public Student(String firstName, String lastName, double grade, String department)
    {
        if (firstName != null || firstName != ""
                && lastName != null || lastName != ""
                && grade > 0.0
                && department != null || department != "")
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.grade = grade;
            this.department = department;
        }
    }

    public void setFirstName(String firstName)
    {
        if (firstName != null || firstName != "")
        {
            this.firstName = firstName;
        }
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName)
    {
        if (lastName != null || lastName != "")
        {
            this.lastName = lastName;
        }
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setGrade(double grade)
    {
        if (grade > 0.0)
        {
            this.grade = grade;
        }
    }

    public double getGrade()
    {
        return this.grade;
    }

    public void setDepartment(String department)
    {
        if (department != null || department != "")
        {
            this.department = department;
        }
    }

    public String getDepartment()
    {
        return this.department;
    }

    public String getName()
    {
        return this.getFirstName() + "\t" + this.getLastName();
    }

    public String toString()
    {
        Formatter format = new Formatter();
        format.format("%.2f", this.getGrade());
        return this.getName() + "\t" + format + "\t" + this.getDepartment();
    }

    public boolean isEmpty()
    {
        if (this.getName() == null || this.getName() == ""
        && this.getGrade() < 0.0
        && this.getDepartment() == null || this.getDepartment() == "")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean equals(Student student)
    {
        if (!student.isEmpty()
            && this.getName().equals(student.getName())
            && this.getGrade() == student.getGrade()
            && this.getDepartment().equals( student.getDepartment()) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
