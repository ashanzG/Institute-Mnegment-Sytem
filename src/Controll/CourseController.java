package Controll;

import Model.Course;
import View.CourseView;
import dash.MainJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CourseController {
    private final Course model;
    private final CourseView view;

    public CourseController(Course model, CourseView view) {
        this.model = model;
        this.view = view;

        loadCourses();

        view.btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
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

    private void loadCourses() {
        DefaultTableModel tableModel = (DefaultTableModel) view.tblCourses.getModel();
        tableModel.setRowCount(0);
        for (String[] course : model.getAllCourses()) {
            tableModel.addRow(course);
        }
    }

    private void addCourse() {
        String id = view.txtCourseID.getText();
        String name = view.txtCourseName.getText();
        String duration = view.txtDuration.getText();

        if (model.insertCourse(id, name, duration)) {
            JOptionPane.showMessageDialog(view, "Course added successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add course.");
        }
    }

    private void updateCourse() {
        String id = view.txtCourseID.getText();
        String name = view.txtCourseName.getText();
        String duration = view.txtDuration.getText();

        if (model.updateCourse(id, name, duration)) {
            JOptionPane.showMessageDialog(view, "Course updated successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update course.");
        }
    }

    private void deleteCourse() {
        String id = view.txtCourseID.getText();

        if (model.deleteCourse(id)) {
            JOptionPane.showMessageDialog(view, "Course deleted successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete course.");
        }
    }
    
    private void generateReport() {
          try {
              // Initialize connection
              Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/institut", "kavindu", "kavindu123");

            // Load and compile Jasper report
            JasperDesign jdesign = JRXmlLoader.load("E:\\eadf\\inst\\JavaApplication3\\src\\Reports\\CourseReport.jrxml");
            String query = "SELECT * FROM course";
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
