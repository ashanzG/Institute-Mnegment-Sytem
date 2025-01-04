package Controll;
// Controller Class: AttendanceController.java
import Model.Attendance;
import View.AttendanceView;
import dash.MainJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        
        view.btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
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
    
    private void generateReport() {
        try {
            // Initialize connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");

            // Load and compile Jasper report
            JasperDesign jdesign = JRXmlLoader.load("E:\\eadf\\inst\\JavaApplication3\\src\\Reports\\AttendanceReport.jrxml");
            String query = "SELECT * FROM attendan";
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);

            // Display the report
            JasperViewer.viewReport(jprint, false);
        } catch (SQLException | JRException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
