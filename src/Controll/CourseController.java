package Controll;

import Model.Course;
import View.CourseView;
import dash.MainJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
