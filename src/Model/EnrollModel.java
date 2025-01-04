package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollModel {

    private Connection con;

    public EnrollModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            con = null; // Set to null explicitly if connection fails
            System.err.println("Database connection failed: " + ex.getMessage());
        }
    }

    public List<String[]> getAllenroll() {
        List<String[]> enroll = new ArrayList<>();
        if (con == null) {
            System.err.println("Database connection is not established.");
            return enroll;
        }

        String query = "SELECT * FROM enroll";
        try (PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                enroll.add(new String[]{
                        rs.getString("eid"),
                        rs.getString("studentName"),
                        rs.getString("courseName"),
                        rs.getString("enrolldate")
                });
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving enrollments: " + ex.getMessage());
        }
        return enroll;
    }

    public boolean insertenroll(String id, String sname, String cname, String date) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }

        String query = "INSERT INTO enroll (eid, studentName, courseName, enrolldate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            pst.setString(2, sname);
            pst.setString(3, cname);
            pst.setString(4, date);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error inserting enrollment: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateenroll(String id, String sname, String cname, String date) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }

        String query = "UPDATE enroll SET studentName = ?, courseName = ?, enrolldate = ? WHERE eid = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, sname);
            pst.setString(2, cname);
            pst.setString(3, date);
            pst.setString(4, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error updating enrollment: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteenroll(String id) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }

        String query = "DELETE FROM enroll WHERE eid = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error deleting enrollment: " + ex.getMessage());
            return false;
        }
    }
}
