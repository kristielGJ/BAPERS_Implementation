/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.admin.userAccount.UserAccount;
import model.database.BackupManager;
import software.amazon.awssdk.services.backup.model.BackupJob;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author msy
 */
public class BackupManagementPanel extends javax.swing.JPanel {

    BackupManager backupManager = new BackupManager();
    private int width;
    private int height;
    private GUI f;
    private JPanel lastPanel;

    /**
     * Creates new form BackupManagement
     */
    public BackupManagementPanel(int width, int height, GUI f) {
        this.width = width;
        this.height = height;
        this.f = f;
        this.lastPanel = f.getCurrentPanel();
        f.setCurrentPanel(this);
        initComponents();
    }

    private void initComponents() {

        restoreButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        backupTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        pageTitle = new javax.swing.JLabel();
        ascendingComboBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        intervalButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(157, 195, 230));

        restoreButton.setBackground(new java.awt.Color(1, 23, 71));
        restoreButton.setForeground(new java.awt.Color(157, 195, 230));
        restoreButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        restoreButton.setText("Restore");
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(1, 23, 71));
        removeButton.setForeground(new java.awt.Color(157, 195, 230));
        removeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(1, 23, 71));
        refreshButton.setForeground(new java.awt.Color(157, 195, 230));
        refreshButton.setText("Refresh");
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        backupTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "ID", "Status", "Completion Time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(backupTable);

        backButton.setBackground(new java.awt.Color(1, 23, 71));
        backButton.setForeground(new java.awt.Color(157, 195, 230));
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        pageTitle.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        pageTitle.setForeground(new java.awt.Color(1, 23, 71));
        pageTitle.setText("Backup Management");

        ascendingComboBox.setBackground(new java.awt.Color(1, 23, 71));
        ascendingComboBox.setForeground(new java.awt.Color(157, 195, 230));
        ascendingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        ascendingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ascendingComboBoxActionPerformed(e);
            }
        });

        addButton.setBackground(new java.awt.Color(1, 23, 71));
        addButton.setForeground(new java.awt.Color(157, 195, 230));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        intervalButton.setBackground(new java.awt.Color(1, 23, 71));
        intervalButton.setForeground(new java.awt.Color(157, 195, 230));
        intervalButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        intervalButton.setText("Interval");
        intervalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(backButton))
                                        .addComponent(jScrollPane1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(pageTitle)
                                                .addGap(18, 18, 18)
                                                .addComponent(ascendingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(intervalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(restoreButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(refreshButton)))
                                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ascendingComboBox, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(intervalButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(restoreButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(removeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
        );
        populateTable();
    }// </editor-fold>

    private void ascendingComboBoxActionPerformed(ActionEvent e) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(backupTable.getModel());
        backupTable.setRowSorter(sorter);
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

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) backupTable.getModel();
        model.setRowCount(0);
        for (BackupJob job : backupManager.getAllBackups()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                            .withLocale( Locale.UK )
                            .withZone( ZoneId.systemDefault() );
            String format = "";
            if (job.expectedCompletionDate() == null && job.completionDate() == null) {
                format = "N/A";
            }else if (job.expectedCompletionDate() == null && job.completionDate() != null) {
                format = formatter.format(job.completionDate());
            }else{
                format = formatter.format(job.expectedCompletionDate());
            }
            model.addRow(new Object[]{job.backupJobId(), job.stateAsString(), format});
        }
    }

    private void removeButtonActionPerformed(ActionEvent evt) {
        if (backupTable.getSelectedRow() != -1) {
            String id = (String) getRow(backupTable.getSelectedRow())[0];
            if (!backupManager.abortBackup(id)) {
                JOptionPane.showMessageDialog(
                        this,
                        "This entry is already completed or aborted already!",
                        "BAPERS", JOptionPane.ERROR_MESSAGE
                );
            }
            populateTable();
        }
    }

    private void refreshButtonActionPerformed(ActionEvent evt) {
        populateTable();
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        if (lastPanel == null) return;
        f.setLastPanel(lastPanel);
        f.getLastPanel().setVisible(true);
        f.remove(f.getCurrentPanel());
        f.setCurrentPanel(lastPanel);
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        backupManager.startBackup();
        populateTable();
    }

    private Object[] getRow(int row) {
        Object[] rowData = new Object[backupTable.getColumnCount()];
        for (int i = 0; i < backupTable.getColumnCount(); i++) {
            rowData[i] = backupTable.getValueAt(row, i);
        }
        return rowData;
    }

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (backupTable.getSelectedRow() != -1) {
            String id = (String) getRow(backupTable.getSelectedRow())[0];
            String recoveryArn = backupManager.getJobFromId(id).recoveryPointArn();
            String idRestore = backupManager.restoreBackup(recoveryArn);
            if (!idRestore.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Started a restore at ID: " + idRestore,
                        "BAPERS", JOptionPane.INFORMATION_MESSAGE
                );
            }
            populateTable();
        }
    }

    private void openDialog(JPanel panel, String title) {
        final JDialog frame = new JDialog(f, title, true);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void intervalButtonActionPerformed(java.awt.event.ActionEvent evt) {
        BackupInterval updateStaffPanel = new BackupInterval(backupTable, backupManager);
        openDialog(updateStaffPanel, "Change Database Interval");
    }


    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> ascendingComboBox;
    private javax.swing.JButton backButton;
    private javax.swing.JTable backupTable;
    private javax.swing.JButton intervalButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pageTitle;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton restoreButton;
    // End of variables declaration
}
