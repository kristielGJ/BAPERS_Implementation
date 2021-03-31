/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.Controller;
import model.database.I_Bapers;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mushfikur
 */
public class StaffManagementPanel extends javax.swing.JPanel {
    private GUI f;
    private JPanel lastPanel;
    private I_Bapers controller;

    /**
     * Creates new form StaffManagementPanel
     */
    public StaffManagementPanel(int width, int height, GUI f, I_Bapers controller) {
        this.controller = controller;
        initComponents();
        this.f = f;
        this.lastPanel = f.getCurrentPanel();
        f.setCurrentPanel(this);
    }

    private void initComponents() {
        pageTitle = new javax.swing.JLabel();
        ascendingComboBox = new javax.swing.JComboBox<>();
        sortByLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        saveButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));

        pageTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        pageTitle.setText("Staff Management");
        pageTitle.setForeground(new java.awt.Color(1, 23, 71));

        ascendingComboBox.setBackground(Style.DARK_BLUE);
        ascendingComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ascendingComboBox.setForeground(Style.LIGHT_BLUE);
        ascendingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        ascendingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingComboBoxPerformed(e);
            }
        });
        //TODO: ascendingComboBox.add


        this.setBackground(Style.LIGHT_BLUE);

        addButton.setBackground(Style.DARK_BLUE);
        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setForeground(Style.LIGHT_BLUE);
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(Style.DARK_BLUE);
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateButton.setForeground(Style.LIGHT_BLUE);
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(Style.DARK_BLUE);
        removeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removeButton.setForeground(Style.LIGHT_BLUE);
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        refreshButton.setBackground(Style.DARK_BLUE);
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refreshButton.setForeground(Style.LIGHT_BLUE);
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Name", "Email", "Phone", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffTable.setGridColor(Style.DARK_BLUE);
        staffTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(staffTable);
        controller.populateStaffTable(staffTable);

        saveButton.setBackground(Style.DARK_BLUE);
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveButton.setForeground(Style.LIGHT_BLUE);
        saveButton.setText("Save");
        backButton.setBackground(Style.DARK_BLUE);
        backButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backButton.setForeground(Style.LIGHT_BLUE);
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(pageTitle)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                                .addComponent(sortByLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ascendingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(refreshButton))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(saveButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backButton)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(removeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sortByLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ascendingComboBox, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57))
        );
    }

    private void ascendingComboBoxPerformed(ActionEvent evt) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(staffTable.getModel());
        staffTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSort = 0;
        if (ascendingComboBox.getSelectedItem() == "Ascending"){
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
        }
        else {
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
        }
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }


    private void removeButtonActionPerformed(ActionEvent evt) {
        if (staffTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an entry to delete!",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }else{
            if (controller.removeStaffMember((Integer) staffTable.getValueAt(staffTable.getSelectedRow(), 0))) {
                controller.populateStaffTable(staffTable);
            }else{
                JOptionPane.showMessageDialog(
                        this,
                        "There was an error trying to remove your entry.",
                        "BAPERS", JOptionPane.ERROR_MESSAGE
                );
            }

        }
    }

    private void refreshButtonActionPerformed(ActionEvent evt) {
        controller.populateStaffTable(staffTable);
    }

    private void openDialog(JPanel panel, String title) {
        final JDialog frame = new JDialog(f, title, true);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        openDialog(new AddStaffPanel(controller, staffTable), "Add Staff");
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        if (lastPanel == null) return;
        f.setLastPanel(lastPanel);
        f.getLastPanel().setVisible(true);
        f.remove(f.getCurrentPanel());
        f.setCurrentPanel(lastPanel);
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
        UpdateStaffPanel updateStaffPanel = new UpdateStaffPanel(controller, staffTable);
        openDialog(updateStaffPanel, "Update Staff");
    }

    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> ascendingComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sortByLabel;
    private javax.swing.JTable staffTable = new JTable();
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
