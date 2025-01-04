package Model;

import dash.MainJFrame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    
    private Connection con;

public StudentModel() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");
        System.out.println("Database connected successfully.");
    } catch (ClassNotFoundException | SQLException ex) {
        con = null; // Set to null explicitly if connection fails
    }
}


public List<String[]> getAllCourses() {
    List<String[]> courses = new ArrayList<>();
    if (con == null) {
        System.err.println("Database connection is not established.");
        return courses;
    }
    try (PreparedStatement pst = con.prepareStatement("SELECT * FROM student");
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            courses.add(new String[]{
                    rs.getString("studentID"),
                    rs.getString("studentName"),
                    rs.getString("email"),
                    rs.getString("address")
            });
        }
    } catch (SQLException ex) {
    }
    return courses;
}


    public boolean insertCourse(String id, String name, String email,String address) {
        try (PreparedStatement pst = con.prepareStatement("INSERT INTO student (studentID, studentName, email,address) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, address);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateCourse(String id, String name, String email,String address) {
        try (PreparedStatement pst = con.prepareStatement("UPDATE student SET studentName = ?, email = ?, address = ? WHERE studentID = ?")) {
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, address);
            pst.setString(3, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deleteCourse(String id) {
        try (PreparedStatement pst = con.prepareStatement("DELETE FROM student WHERE studentID = ?")) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }
}
