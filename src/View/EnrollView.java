package View;

import Controll.EnrollController;
import Model.EnrollModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EnrollView extends JFrame {
    public JTextField txteid, txtesn, txtec, txtEnrollDate;
    public JTable tblenroll;
    public JButton btnInsert, btnUpdate, btnDelete, btnDashboard;

    public EnrollView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Enroll Management");
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED);
        JLabel lblHeader = new JLabel("Enroll");
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
        JLabel lbleid = new JLabel("EId:");
        txteid = new JTextField(15);

        JLabel lblstudentName = new JLabel("Student Name:");
        txtesn = new JTextField(15);

        JLabel lblcourseName = new JLabel("Course Name:");
        txtec = new JTextField(15);

        JLabel lblEnrollDate = new JLabel("Enroll Date:");
        txtEnrollDate = new JTextField(15);

        // Adding components to the input panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lbleid, gbc);
        gbc.gridx = 1;
        inputPanel.add(txteid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblstudentName, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtesn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblcourseName, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtec, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(lblEnrollDate, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtEnrollDate, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Table Panel
        tblenroll = new JTable(new DefaultTableModel(
            new String[]{"EId", "Student Name", "Course Name", "Enroll Date"}, 0
        ));
        JScrollPane scrollPane = new JScrollPane(tblenroll);
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
        EnrollModel model = new EnrollModel();
        EnrollView view = new EnrollView();
        EnrollController controller = new EnrollController(model, view);

        view.setVisible(true);
    }
}
