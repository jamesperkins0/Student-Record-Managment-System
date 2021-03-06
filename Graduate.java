
package student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graduate extends Student {

    private String thesisTitle;
    private String thesisAdvisor;

    public Graduate() {
        super();
    }

    public Graduate(String first, String last, int id, double gpa,
            String stat, String mentorName, String thesisTit, String thesisAdv) {
        super(first, last, id, gpa, stat, mentorName, thesisTit, thesisAdv);
    }

    public String getThesisAdvisor() {
        return thesisAdvisor;
    }

    public void setThesisAdvisor(String thesisA) {
        thesisAdvisor = thesisA;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisT) {
        thesisTitle = thesisT;
    }

    @Override
    public boolean query() {

        GetConnection gc = new GetConnection();
        Statement stmt = null;
        Connection conn = gc.getSimpleConnection();
        try {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String select = "SELECT * from student";
            ResultSet rs = stmt.executeQuery(select);
            System.out.println("Student Records:");
            while (rs.next()) {
                System.out.println("Student ID#:\t" + rs.getString("studentID"));
                System.out.println("First Name:\t" + rs.getString("firstName"));
                System.out.println("Last Name:\t" + rs.getString("lastName"));
                System.out.println("Grade Avg:\t" + rs.getString("gpa"));
                System.out.println("Status:\t" + rs.getString("status"));
                System.out.println("Mentor:\t" + rs.getString("mentor"));
                System.out.println("Level:\t" + rs.getString("level"));
                System.out.println("Title:\t" + rs.getString("thesisTitle"));
                System.out.println("Advisor:\t" + rs.getString("thesisAdvisor"));
                System.out.println("Company:\t" + rs.getString("company"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    @Override
    public boolean update() {
        GetConnection gc = new GetConnection();
        Statement stmt = null;
        Connection conn = gc.getSimpleConnection();
        try {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("select * from student where studentID ='" + getStudentID() + "'");

            boolean isEmpty = !rs.first();

            if (isEmpty == true) {
                System.out.println("Student doesn't exist, Please add first");
            } else {
                String update = "UPDATE student "
                        + "SET firstName= '" + getFirstName() + "',"
                        + "lastName='" + getLastName() + "',"
                        + "gpa='" + getGPA() + "',"
                        + "status='" + getStatus() + "',"
                        + "mentor='" + getMentor() + "'"
                        + "where studentID ='" + getStudentID() + "'";
                stmt.execute(update);
                System.out.println("Student Records Updated:");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete() {
        GetConnection gc = new GetConnection();
        Statement stmt = null;
        Connection conn = gc.getSimpleConnection();
        try {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from student where studentID ='" + getStudentID() + "'");

            boolean isEmpty = !rs.first();

            if (isEmpty == true) {
                System.out.println("Student doesn't exist");
            } else {
                String delete = "DELETE from student where studentID ='" + getStudentID() + "'";
                stmt.execute(delete);
                System.out.println("Student Records Deleted:");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    @Override
    public double calculateTuition(int hours, int status) {
        double rate;

        if (status == 1) {
            rate = 300;
        } else {
            rate = 350;
        }
        double tution = rate * hours;
        return tution;
    }

    @Override
    public String add() {
        GetConnection gc = new GetConnection();
        Connection con = gc.getSimpleConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from student where studentID ='" + getStudentID() + "'");

            boolean isEmpty = !rs.first();

            if (isEmpty == false) {
                System.out.println("Student with this id Already exist");
            } else {
                stmt.execute("INSERT into student"
                        + "(studentID, firstName, lastName, gpa, status, mentor,"
                        + "level, thesisTitle, thesisAdvisor,company)"
                        + "VALUES ('"
                        + getStudentID() + "','"
                        + getFirstName() + "','"
                        + getLastName() + "','"
                        + getGPA() + "','"
                        + getStatus() + "','"
                        + getMentor() + "','"
                        + "Senior" + "','"
                        + getThesisTitle() + "','"
                        + getThesisAdvisor() + "','"
                        + "NULL" + "')");
                System.out.println("Student added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Graduate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Success";
    }
}
