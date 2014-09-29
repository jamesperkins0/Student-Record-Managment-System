package student;

public abstract class Student {
    private String firstName;
    private String lastName;
    private int studentID;
    private double GPA;
    private String status;
    private String mentor;
    /**
     * @param args the command line arguments
     */
    public Student() {
  
    }
    
    public Student(String first, String last, int id, double gpa,
            String stat, String mentorName)
        {
        firstName = first;
        lastName = last;
        studentID = id;
        GPA = gpa;
        status = stat;
        mentor = mentorName;
    }
    
    public Student(String first, String last, int id, double gpa,
            String stat, String mentorName, String thesisTit, String ThesisAdv)
        {
        firstName = first;
        lastName = last;
        studentID = id;
        GPA = gpa;
        status = stat;
        mentor = mentorName;
    }
    
    //getters and setters
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setFirstName(String first)
    {
 
        firstName = first;
 
    }
    public void setLastName(String last)
    {
        lastName = last;
    }
    public int getStudentID()
    {
        return studentID;
    }
    public void setStudentID(int id)
    {
        studentID = id;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String s)
    {
        status = s;
    }
    public double getGPA()
    {
        return GPA;
    }
    public void setGPA(double gpa)
    {
        GPA = gpa;
    }
    public String getMentor()
    {
        return mentor;
    }
    public void setMentor(String mentorName)
    {
        mentor = mentorName;
    }
    
    public abstract double calculateTuition(int hour, int status);
    
    public abstract String add();
 
    public abstract boolean update();
 
    public abstract boolean delete();
 
    public abstract boolean query();
    
}
