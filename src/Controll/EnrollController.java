package Controll;

import Model.EnrollModel;
import View.EnrollView;
import dash.MainJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class EnrollController {
    private final EnrollModel model;
    private final EnrollView view;

    /**
     * Constructor
     *
     * @param model EnrollModel instance
     * @param view  EnrollView instance
     */
    public EnrollController(EnrollModel model, EnrollView view) {
        this.model = model;
        this.view = view;

        // Load initial data into the table
        loadEnroll();

        // Attach action listeners
        view.btnInsert.addActionListener(this::handleInsert);
        view.btnUpdate.addActionListener(this::handleUpdate);
        view.btnDelete.addActionListener(this::handleDelete);
        view.btnReport.addActionListener(this::handleReport);
        view.btnDashboard.addActionListener(this::handledash);
    }

    private void loadEnroll() {
        DefaultTableModel tableModel = (DefaultTableModel) view.tblenroll.getModel();
        tableModel.setRowCount(0); // Clear existing rows
        for (String[] enroll : model.getAllenroll()) {
            tableModel.addRow(enroll);
        }
    }

    private void handleInsert(ActionEvent e) {
        String id = view.txteid.getText();
        String studentName = view.txtesn.getText();
        String courseName = view.txtec.getText();
        String date = view.txtEnrollDate.getText(); // Ensure txtEnrollDate is public in EnrollView

        if (model.insertenroll(id, studentName, courseName, date)) {
            JOptionPane.showMessageDialog(view, "Enroll added successfully.");
            loadEnroll();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add Enroll.");
        }
    }

    private void handleUpdate(ActionEvent e) {
        String id = view.txteid.getText();
        String studentName = view.txtesn.getText();
        String courseName = view.txtec.getText();
        String date = view.txtEnrollDate.getText();

        if (model.updateenroll(id, studentName, courseName, date)) {
            JOptionPane.showMessageDialog(view, "Enroll updated successfully.");
            loadEnroll();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update Enroll.");
        }
    }

    private void handleDelete(ActionEvent e) {
        String id = view.txteid.getText();

        if (model.deleteenroll(id)) {
            JOptionPane.showMessageDialog(view, "Enroll deleted successfully.");
            loadEnroll();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete Enroll.");
        }
    }
    
     private void handledash(ActionEvent e) {
       new MainJFrame().setVisible(true);
                view.dispose(); 

    }
     
      private void handleReport(ActionEvent e) {
       try {
           // Initialize connection
           Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");

            // Load and compile Jasper report
            JasperDesign jdesign = JRXmlLoader.load("E:\\eadf\\inst\\JavaApplication3\\src\\Reports\\EnrollReport.jrxml");
            String query = "SELECT * FROM enroll";
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
