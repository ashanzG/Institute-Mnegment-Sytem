package Controll;

import Model.FeeModel;
import View.FeeView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeeController {
    private final FeeModel model;
    private final FeeView view;

    /**
     * Constructor
     *
     * @param model FeeModel instance
     * @param view  FeeView instance
     */
    public FeeController(FeeModel model, FeeView view) {
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model and View cannot be null.");
        }

        this.model = model;
        this.view = view;

        loadFees();

        view.btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFee();
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFee();
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFee();
            }
        });
    }

    private void loadFees() {
        DefaultTableModel tableModel = (DefaultTableModel) view.tblfee.getModel();
        tableModel.setRowCount(0);
        for (String[] fee : model.getAllFees()) {
            tableModel.addRow(fee);
        }
    }

    private void addFee() {
        String id = view.txtfid.getText();
        String name = view.txtfstn.getText();
        String amount = view.txtfa.getText();

        if (id.isEmpty() || name.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.insertFee(id, name, amount)) {
            JOptionPane.showMessageDialog(view, "Fee added successfully.");
            loadFees();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add Fee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFee() {
        String id = view.txtfid.getText();
        String name = view.txtfstn.getText();
        String amount = view.txtfa.getText();

        if (id.isEmpty() || name.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.updateFee(id, name, amount)) {
            JOptionPane.showMessageDialog(view, "Fee updated successfully.");
            loadFees();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update Fee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFee() {
        String id = view.txtfid.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Fee ID is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.deleteFee(id)) {
            JOptionPane.showMessageDialog(view, "Fee deleted successfully.");
            loadFees();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to delete Fee.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
