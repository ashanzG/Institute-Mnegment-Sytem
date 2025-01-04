package View;

import Controll.CourseController;
import Model.Course;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseView extends JFrame {
    public JTextField txtCourseID, txtCourseName, txtDuration;
    public JTable tblCourses;
    public JButton btnInsert, btnUpdate, btnDelete, btnDashboard;

    public CourseView() {
        setTitle("Course Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED);
        JLabel lblHeader = new JLabel("Course Management");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels and Text Fields
        JLabel lblCourseID = new JLabel("Course ID:");
        txtCourseID = new JTextField(15);

        JLabel lblCourseName = new JLabel("Course Name:");
        txtCourseName = new JTextField(15);

        JLabel lblDuration = new JLabel("Duration:");
        txtDuration = new JTextField(15);

        // Adding components to the input panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblCourseID, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtCourseID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblCourseName, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtCourseName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblDuration, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtDuration, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Table Panel
        tblCourses = new JTable(new DefaultTableModel(
            new String[]{"Course ID", "Course Name", "Duration"}, 0
        ));
        JScrollPane scrollPane = new JScrollPane(tblCourses);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnDashboard = new JButton("Dashboard");

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnDashboard);

        add(buttonPanel, BorderLayout.SOUTH);

        // Finalizing the frame
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Course model = new Course();
        CourseView view = new CourseView();
        CourseController controller = new CourseController(model, view);

        view.setVisible(true);
    }
}
