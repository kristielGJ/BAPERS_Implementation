/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.Controller;
import model.database.I_Bapers;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author msy
 */
public class UpdateStaffPanel extends javax.swing.JPanel {
    private I_Bapers controller;
    private JTable staffTable;
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(new String[] { "Office Manager", "Shift Manager", "Receptionist", "Technician" });

    /**
     * Creates new form UpdateStaffPanel
     */
    public UpdateStaffPanel(I_Bapers controller, JTable staffTable) {
        this.controller = controller;
        this.staffTable = staffTable;
        initComponents();
    }

    private Integer[] getAllIds() {
        Integer[] ids = new Integer[staffTable.getRowCount()];
        for (int i = 0; i < staffTable.getRowCount(); i++) {
            ids[i] = (Integer) staffTable.getValueAt(i, 0);
        }
        return ids;
    }

    public int getRowFromId(int id) {
        for (int i = 0; i < staffTable.getRowCount(); i++) {
            if (staffTable.getValueAt(i, 0).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private Object[] getRow(int row) {
        Object[] rowData = new Object[staffTable.getColumnCount()];
        for (int i = 0; i < staffTable.getColumnCount(); i++) {
            rowData[i] = staffTable.getValueAt(row, i);
        }
        return rowData;
    }

    private void fillInputs(int row) {
        Object[] valuesFromRow = getRow(row);
        if (valuesFromRow.length > 0) {
            nameInput.setText((String) valuesFromRow[1]);
            emailInput.setText((String) valuesFromRow[2]);
            phoneInput.setText((String) valuesFromRow[3]);
            for (int i = 0; i < roleDropdown.getItemCount(); i++) {
                if (comboBoxModel.getElementAt(i).equals(valuesFromRow[4])) {
                    roleDropdown.setSelectedItem(comboBoxModel.getElementAt(i));
                }
            }
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        pageTitle = new java.awt.Label();
        cancelButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        phoneInput = new javax.swing.JTextField();
        emailInput = new javax.swing.JTextField();
        nameInput = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        roleDropdown = new javax.swing.JComboBox<>();
        idLabel = new javax.swing.JLabel();
        SpinnerListModel model = new SpinnerListModel(getAllIds());
        idSpinner = new javax.swing.JSpinner(model);
        passwordLabel = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        pageTitle.setAlignment(java.awt.Label.CENTER);
        pageTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pageTitle.setText("Update Staff");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        phoneInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        emailInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nameInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameLabel.setText("Name:");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emailLabel.setText("Email:");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phoneLabel.setText("Phone:");

        roleLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        roleLabel.setText("Role:");

        roleDropdown.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        roleDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Office Manager", "Shift Manager", "Receptionist", "Technician" }));

        idLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idLabel.setText("ID to Update:");

        idSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int rowToFill = getRowFromId((int) idSpinner.getValue());
                fillInputs(rowToFill);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passwordLabel.setText("Password:");

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pageTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 254, Short.MAX_VALUE)
                                                .addComponent(updateButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(roleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(idLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(emailInput)
                                                        .addComponent(nameInput)
                                                        .addComponent(phoneInput)
                                                        .addComponent(roleDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(idSpinner)
                                                        .addComponent(jPasswordField1))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameLabel))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailLabel)
                                        .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phoneLabel))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(roleDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(roleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 301, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        if (staffTable.getSelectedRow() != -1) {
            idSpinner.setValue(getRow(staffTable.getSelectedRow())[0]);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
        int id = (int) idSpinner.getValue();
        String name = nameInput.getText();
        String email = emailInput.getText();
        String phone = phoneInput.getText();
        String password = new String(jPasswordField1.getPassword());
        String role = roleDropdown.getSelectedItem().toString();
        if (controller.updateStaffMember(new Object[]{id, name, email, phone, password, role})) {
            controller.populateStaffTable(staffTable);
        }else{
            JOptionPane.showMessageDialog(
                    this,
                    "There was an error with your addition",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton updateButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JSpinner idSpinner;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel nameLabel;
    private java.awt.Label pageTitle;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField phoneInput;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JComboBox<String> roleDropdown;
    private javax.swing.JLabel roleLabel;
    // End of variables declaration
}
