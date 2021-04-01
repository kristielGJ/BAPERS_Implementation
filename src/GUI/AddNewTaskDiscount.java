/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.DB_Connection;
import model.database.I_Bapers;
import model.discounts.variable_discount.VariableDiscount;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author g_jah
 */
public class AddNewTaskDiscount extends javax.swing.JPanel {
    private GUI f;
    private JPanel lastPanel;
    private I_Bapers bapers;
    int acc_no;
    private static PreparedStatement Stm = null;

    /**
     * Creates new form AddNewTaskDiscount
     */
    public AddNewTaskDiscount(int width, int height, I_Bapers bapers,GUI f, int acc_no) {
        this.acc_no=acc_no;
        this.f=f;
        this.lastPanel= f.getCurrentPanel();
        f.setCurrentPanel(this);
        this.bapers=bapers;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UpdateButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        EnterDiscountRate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        task_list_input = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(157, 195, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 23, 71));
        jLabel1.setText("Add New Task Discount");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 23, 71));
        jLabel2.setText("Discount Rate");

        UpdateButton.setBackground(new java.awt.Color(1, 23, 71));
        UpdateButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        UpdateButton.setForeground(new java.awt.Color(157, 195, 230));
        UpdateButton.setText("Save");
        UpdateButton.setBorderPainted(false);
        UpdateButton.setFocusPainted(false);
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt, acc_no);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 23, 71));
        jLabel3.setText("Bapers");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 23, 71));
        jLabel4.setText("Task");

        task_list_input.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        task_list_input.setModel(new javax.swing.DefaultComboBoxModel<>(bapers.existingTasks()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(task_list_input, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(28, 28, 28)
                                    .addComponent(EnterDiscountRate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel3)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(EnterDiscountRate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(task_list_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(UpdateButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt, int acc_no) {//GEN-FIRST:event_UpdateButtonActionPerformed
        ArrayList<String> TaskNames = new ArrayList<String>();
        TaskNames=bapers.ManageVariableTable(acc_no);
        if((!(task_list_input.getSelectedItem() == "Select Task")&&(!EnterDiscountRate.getText().isEmpty()))){
            if (TaskNames.contains(String.valueOf(task_list_input.getSelectedItem()))){
                JOptionPane.showMessageDialog(
                        this,
                        "This Task has an assigned discount.",
                        "BAPERS", JOptionPane.ERROR_MESSAGE
                );

            }else{
                bapers.createVariableDiscount(acc_no, Double.parseDouble(EnterDiscountRate.getText()), String.valueOf(task_list_input.getSelectedItem()));
            }
            //bapers.createVariableDiscount(acc_no, Double.parseDouble(EnterDiscountRate.getText()), String.valueOf(task_list_input.getSelectedItem()));
            task_list_input.setSelectedItem("Select Task");
            EnterDiscountRate.setText("");

        } else{
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill out the fields.",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_UpdateButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EnterDiscountRate;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> task_list_input;
    // End of variables declaration//GEN-END:variables
}
