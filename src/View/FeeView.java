package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FeeView extends JFrame {
    public JTextField txtfid, txtfstn, txtfa;
    public JTable tblfee;
    public JButton btnInsert, btnUpdate, btnDelete;

    public FeeView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fee Management System");
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED);
        JLabel lblHeader = new JLabel("Fee Management System");
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
        JLabel lblfid = new JLabel("Fee ID:");
        txtfid = new JTextField(15);

        JLabel lblStudentName = new JLabel("Student Name:");
        txtfstn = new JTextField(15);

        JLabel lblAmount = new JLabel("Amount:");
        txtfa = new JTextField(15);

        // Adding components to the input panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblfid, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtfid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblStudentName, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtfstn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblAmount, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtfa, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Table Panel
        tblfee = new JTable(new DefaultTableModel(new String[]{"Fee ID", "Student Name", "Amount"}, 0));
        JScrollPane scrollPane = new JScrollPane(tblfee);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // Finalizing the frame
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FeeView view = new FeeView();
            view.setVisible(true);
        });
    }
}
