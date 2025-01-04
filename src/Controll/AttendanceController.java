package Controll;
// Controller Class: AttendanceController.java
import Model.Attendance;
import View.AttendanceView;
import dash.MainJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class AttendanceController {
    private final Attendance model;
    private final AttendanceView view;

    public AttendanceController(Attendance model, AttendanceView view) {
        this.model = model;
        this.view = view;

        loadTableData();

        // Add action listeners
        view.btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertAttendance();
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAttendance();
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAttendance();
            }
        });
        view.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new MainJFrame().setVisible(true);
                view.dispose(); 
            }
        });
    }

    private void loadTableData() {
        try {
            DefaultTableModel modelTable = (DefaultTableModel) view.attendanceTable.getModel();
            modelTable.setRowCount(0);

            for (String[] row : model.getAttendanceData()) {
                modelTable.addRow(row);
            }
        } catch (SQLException e) {
        }
    }

    private void insertAttendance() {
        try {
            String aid = view.txtAid.getText();
            String studentName = view.txtStudentName.getText();
            String section = view.txtSection.getText();
            String status = (String) view.txtStatus.getSelectedItem();

            model.insertAttendance(aid, studentName, section, status);
            loadTableData();

            clearFields();
        } catch (SQLException e) {
        }
    }

    private void updateAttendance() {
        try {
            String aid = view.txtAid.getText();
            String studentName = view.txtStudentName.getText();
            String section = view.txtSection.getText();
            String status = (String) view.txtStatus.getSelectedItem();

            model.updateAttendance(aid, studentName, section, status);
            loadTableData();

            clearFields();
        } catch (SQLException e) {
        }
    }

    private void deleteAttendance() {
        try {
            String aid = view.txtAid.getText();

            model.deleteAttendance(aid);
            loadTableData();

            clearFields();
        } catch (SQLException e) {
        }
    }
    
    

    private void clearFields() {
        view.txtAid.setText("");
        view.txtStudentName.setText("");
        view.txtSection.setText("");
        view.txtStatus.setSelectedIndex(0);
    }
}
