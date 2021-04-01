/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.BackupManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mushfikur
 */
public class BackupInterval extends javax.swing.JPanel {

    private JTable backupTable;
    private BackupManager backupManager;

    /**
     * Creates new form AddStaff
     */
    public BackupInterval(JTable backupTable, BackupManager backupManager) {
        this.backupTable = backupTable;
        this.backupManager = backupManager;
        initComponents();
    }

    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pageTitle = new java.awt.Label();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2.setText("jButton2");

        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        this.setBackground(Style.LIGHT_BLUE);

        pageTitle.setAlignment(java.awt.Label.CENTER);
        pageTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pageTitle.setText("Change Backup Frequency");
        pageTitle.setForeground(Style.DARK_BLUE);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addButton.setText("Change");
        addButton.setBackground(Style.DARK_BLUE);
        addButton.setForeground(Style.LIGHT_BLUE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        cancelButton.setText("Cancel");
        cancelButton.setBackground(Style.DARK_BLUE);
        cancelButton.setForeground(Style.LIGHT_BLUE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });


        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pageTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 254, Short.MAX_VALUE)
                                                .addComponent(addButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton))
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }

    private void close() {
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    private void addButtonActionPerformed(ActionEvent e) {
        String id = backupManager.changeFrequency((String) jComboBox1.getSelectedItem());
        JOptionPane.showMessageDialog(
                this,
                "Changed frequency: " + id,
                "BAPERS", JOptionPane.INFORMATION_MESSAGE
        );
        close();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        close();
    }

    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.Label pageTitle;
}
