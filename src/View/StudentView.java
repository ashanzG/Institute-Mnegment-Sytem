package View;

import Controll.StudentController;
import Model.StudentModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


  public class StudentView extends JFrame {
    public JTextField txtsid, txtsn, txtsemail, txtsadress;
    public JTable tblStudent;
    public JButton btnInsert, btnUpdate, btnDelete,btnDashboard,btnReport;

    public StudentView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED);
        JLabel lblHeader = new JLabel("Student");
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
        JLabel lblstudentID = new JLabel("Student ID:");
        txtsid = new JTextField(15);

        JLabel lblstudentName = new JLabel("Student Name:");
        txtsn = new JTextField(15);

        JLabel lblemail = new JLabel("Email:");
        txtsemail = new JTextField(15);

        JLabel lbladdress = new JLabel("Address:");
        txtsadress = new JTextField(15);

        // Adding components to the input panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblstudentID, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtsid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblstudentName, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtsn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblemail, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtsemail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(lbladdress, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtsadress, gbc);

        add(inputPanel, BorderLayout.CENTER);

        // Table Panel
        tblStudent = new JTable(new DefaultTableModel(new String[]{"Student ID", "Student Name", "Email", "Address"}, 0));
        JScrollPane scrollPane = new JScrollPane(tblStudent);
        add(scrollPane, BorderLayout.EAST);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnDashboard = new JButton ("Dashboard");
        btnReport = new JButton ("Report");

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReport);
        buttonPanel.add(btnDashboard);
      
        add(buttonPanel, BorderLayout.SOUTH);

        // Finalizing the frame
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentView view = new StudentView(); // Initialize the view first
            StudentModel model = new StudentModel();
            StudentController controller = new StudentController(model, view); // Now pass the initialized view to controller
            view.setVisible(true); // Show the view
        });
    }
}