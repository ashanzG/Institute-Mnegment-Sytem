package View;

// View Class: AttendanceView.java
import Controll.AttendanceController;
import Model.Attendance;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AttendanceView extends JFrame {
    public JTextField txtAid, txtStudentName, txtSection;
    public JComboBox<String> txtStatus;
    public JTable attendanceTable;
    public JButton btnInsert, btnUpdate, btnDelete, btnDashboard,btnReport; // Added btnDashboard declaration

    public AttendanceView() {
        setTitle("Attendance Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize components
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED);
        JLabel lblHeader = new JLabel("Attendance");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);

        txtAid = new JTextField(15);
        txtStudentName = new JTextField(15);
        txtSection = new JTextField(15);
        txtStatus = new JComboBox<>(new String[]{"Present", "Absent"});
        attendanceTable = new JTable(new DefaultTableModel(new String[]{"Aid", "Student Name", "Section", "Status"}, 0));
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnReport = new JButton("Report");
        btnDashboard = new JButton("Dashboard"); // Initialize btnDashboard

        // Layout setup
        JPanel panel = new JPanel();
        panel.add(new JLabel("Aid:"));
        panel.add(txtAid);
        panel.add(new JLabel("Student Name:"));
        panel.add(txtStudentName);
        panel.add(new JLabel("Section:"));
        panel.add(txtSection);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatus);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReport);
        buttonPanel.add(btnDashboard); // Add btnDashboard to buttonPanel

        JScrollPane tableScrollPane = new JScrollPane(attendanceTable);

        // Add components to the layout
        add(headerPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        // Finalize layout
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Attendance model = new Attendance();
        AttendanceView view = new AttendanceView();
        AttendanceController controller = new AttendanceController(model, view);

        view.setVisible(true);
    }
}
