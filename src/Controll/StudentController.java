package Controll;
import Model.StudentModel;
import View.StudentView;
import View.StudentView;
import dash.MainJFrame;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentController {
    private final StudentModel model;
    private final StudentView view;

    /**
     *
     * @param model
     * @param view
     */
    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        loadCourses();

        view.btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
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
        DefaultTableModel tableModel = (DefaultTableModel) view.tblStudent.getModel();
        tableModel.setRowCount(0);
        for (String[] course : model.getAllCourses()) {
            tableModel.addRow(course);
        }
    }

    private void addStudent() {
        String id = view.txtsid.getText();
        String name = view.txtsn.getText();
        String email = view.txtsemail.getText();
        String address = view.txtsadress.getText();

        if (model.insertCourse(id, name, email, address)) {
            JOptionPane.showMessageDialog(view, "Student added successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add Student.");
        }
    }

    private void updateStudent() {
        String id = view.txtsid.getText();
        String name = view.txtsn.getText();
        String email = view.txtsemail.getText();
        String address = view.txtsadress.getText();
        

        if (model.updateCourse(id, name, email, address)) {
            JOptionPane.showMessageDialog(view, "Student updated successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update Student.");
        }
    }

    private void deleteStudent() {
        String id = view.txtsid.getText();

        if (model.deleteCourse(id)) {
            JOptionPane.showMessageDialog(view, "Student deleted successfully.");
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete Student.");
        }
    }
}
