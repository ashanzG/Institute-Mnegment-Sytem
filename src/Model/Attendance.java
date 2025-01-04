package Model;
// Model Class: Attendance.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Attendance {
    private Connection con;

    public Attendance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public List<String[]> getAttendanceData() throws SQLException {
        List<String[]> data = new ArrayList<>();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM attendan");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new String[]{
                rs.getString("aid"),
                rs.getString("studentName"),
                rs.getString("section"),
                rs.getString("status")
            });
        }
        return data;
    }

    public void insertAttendance(String aid, String studentName, String section, String status) throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO attendan (aid, studentName, section, status) VALUES (?, ?, ?, ?)");
        pst.setString(1, aid);
        pst.setString(2, studentName);
        pst.setString(3, section);
        pst.setString(4, status);
        pst.executeUpdate();
    }

    public void updateAttendance(String aid, String studentName, String section, String status) throws SQLException {
        PreparedStatement pst = con.prepareStatement("UPDATE attendan SET studentName = ?, section = ?, status = ? WHERE aid = ?");
        pst.setString(1, studentName);
        pst.setString(2, section);
        pst.setString(3, status);
        pst.setString(4, aid);
        pst.executeUpdate();
    }

    public void deleteAttendance(String aid) throws SQLException {
        PreparedStatement pst = con.prepareStatement("DELETE FROM attendan WHERE aid = ?");
        pst.setString(1, aid);
        pst.executeUpdate();
    }
}
