package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeeModel {

    private Connection con;

    public FeeModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            con = null; // Set to null explicitly if connection fails
            System.err.println("Database connection failed: " + ex.getMessage());
        }
    }

    public List<String[]> getAllFees() {
        List<String[]> fees = new ArrayList<>();
        if (con == null) {
            System.err.println("Database connection is not established.");
            return fees;
        }
        try (PreparedStatement pst = con.prepareStatement("SELECT * FROM fee");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                fees.add(new String[]{
                        rs.getString("fid"),
                        rs.getString("studentName"),
                        rs.getString("amount")
                });
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching fees: " + ex.getMessage());
        }
        return fees;
    }

    public boolean insertFee(String id, String name, String amount) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }
        try (PreparedStatement pst = con.prepareStatement("INSERT INTO fee (fid, studentName, amount) VALUES (?, ?, ?)")) {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, amount);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error inserting fee: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateFee(String id, String name, String amount) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }
        try (PreparedStatement pst = con.prepareStatement("UPDATE fee SET studentName = ?, amount = ? WHERE fid = ?")) {
            pst.setString(1, name);
            pst.setString(2, amount);
            pst.setString(3, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error updating fee: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteFee(String id) {
        if (con == null) {
            System.err.println("Database connection is not established.");
            return false;
        }
        try (PreparedStatement pst = con.prepareStatement("DELETE FROM fee WHERE fid = ?")) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error deleting fee: " + ex.getMessage());
            return false;
        }
    }
}
