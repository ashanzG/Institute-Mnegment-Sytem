package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Course {
    
    private Connection con;

public Course() {
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
    try (PreparedStatement pst = con.prepareStatement("SELECT * FROM course");
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            courses.add(new String[]{
                    rs.getString("courseID"),
                    rs.getString("courseName"),
                    rs.getString("duration")
            });
        }
    } catch (SQLException ex) {
    }
    return courses;
}


    public boolean insertCourse(String id, String name, String duration) {
        try (PreparedStatement pst = con.prepareStatement("INSERT INTO course (courseID, courseName, duration) VALUES (?, ?, ?)")) {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, duration);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateCourse(String id, String name, String duration) {
        try (PreparedStatement pst = con.prepareStatement("UPDATE course SET courseName = ?, duration = ? WHERE courseID = ?")) {
            pst.setString(1, name);
            pst.setString(2, duration);
            pst.setString(3, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deleteCourse(String id) {
        try (PreparedStatement pst = con.prepareStatement("DELETE FROM course WHERE courseID = ?")) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }
}
