/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.database.I_Bapers;
import model.discounts.flexible_discount.FlexibleDiscount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Allows Managment of Variable Discounts of a valued customer
 * Displays a table with all data a customer has once a "manage discount" button is clickd on the Valued Customer Gui Frame
 * allows the user to add new discounts, delete discounts and update existing discounts
 * Also allows the user to go back to the previous page
 * Also alows the user to refresh the page to update the table that populates using the database
 *
 * @author Gera
 */
public class VariableDiscount extends javax.swing.JPanel {
    private GUI f;
    private JPanel lastPanel;
    private I_Bapers bapers;
    /**
     * Creates new form VariableDiscount
     */
    public VariableDiscount(int width, int height, I_Bapers bapers,GUI f, int acc_no) {
        this.f=f;
        this.lastPanel= f.getCurrentPanel();
        this.bapers=bapers;
        f.setCurrentPanel(this);
        initComponents(acc_no);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(int acc_no) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Task_Table = new javax.swing.JTable();
        BackButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        UpdateDiscountButton = new javax.swing.JButton();
        AddDiscountButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        RefreshButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(157, 195, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 23, 71));
        jLabel1.setText("Variable Discount");

        Task_Table.setModel(new javax.swing.table.DefaultTableModel(
            new String [] {
                "Task ID","Task Name", "Discount Rate"
            },0
        ) {
            boolean[] canEdit = new boolean [] {
                false,false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        populateTable(Task_Table, acc_no);
        Task_Table.setGridColor(new java.awt.Color(1, 23, 71));
        Task_Table.setSelectionBackground(new java.awt.Color(230, 238, 255));
        Task_Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Task_Table);

        BackButton.setBackground(new java.awt.Color(1, 23, 71));
        BackButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BackButton.setForeground(new java.awt.Color(157, 195, 230));
        BackButton.setText("Back");
        BackButton.setBorderPainted(false);
        BackButton.setFocusPainted(false);
        BackButton.addMouseListener(new backListener());


        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 23, 71));
        jLabel2.setText("Bapers");

        UpdateDiscountButton.setBackground(new java.awt.Color(1, 23, 71));
        UpdateDiscountButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateDiscountButton.setForeground(new java.awt.Color(157, 195, 230));
        UpdateDiscountButton.setText("Edit");
        UpdateDiscountButton.setActionCommand("");
        UpdateDiscountButton.setBorderPainted(false);
        UpdateDiscountButton.setFocusPainted(false);
        UpdateDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDiscountButtonActionPerformed(evt, Task_Table, acc_no);
            }
        });
        AddDiscountButton.setBackground(new java.awt.Color(1, 23, 71));
        AddDiscountButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AddDiscountButton.setForeground(new java.awt.Color(157, 195, 230));
        AddDiscountButton.setText("Add");
        AddDiscountButton.setActionCommand("");
        AddDiscountButton.setBorderPainted(false);
        AddDiscountButton.setFocusPainted(false);
        AddDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDiscountButtonActionPerformed(evt,acc_no);
            }
        });


        DeleteButton.setBackground(new java.awt.Color(1, 23, 71));
        DeleteButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteButton.setForeground(new java.awt.Color(157, 195, 230));
        DeleteButton.setText("Delete");
        DeleteButton.setActionCommand("");
        DeleteButton.setBorderPainted(false);
        DeleteButton.setFocusPainted(false);
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt, Task_Table, acc_no);
            }
        });

        RefreshButton.setBackground(new java.awt.Color(1, 23, 71));
        RefreshButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        RefreshButton.setForeground(new java.awt.Color(157, 195, 230));
        RefreshButton.setText("Refresh");
        RefreshButton.setBorderPainted(false);
        RefreshButton.setFocusPainted(false);
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt, Task_Table, acc_no);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(282, 282, 282)
                        .addComponent(BackButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                    .addComponent(AddDiscountButton)
                                    .addGap(10, 10, 10)
                                    .addGap(5, 5, 5)
                                    .addComponent(UpdateDiscountButton)
                                .addGap(10, 10, 10)
                                .addGap(5, 5, 5)
                                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(RefreshButton)))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(UpdateDiscountButton)
                        .addComponent(AddDiscountButton)
                        .addComponent(DeleteButton)
                        .addComponent(RefreshButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackButton)
                    .addComponent(jLabel2))
                .addGap(66, 66, 66))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //adding the new task
    private void AddDiscountButtonActionPerformed(java.awt.event.ActionEvent evt,int acc_no) {//GEN-FIRST:event_UpdateDiscountButtonActionPerformed
            AddNewTaskDiscount addNewTaskDiscount = new AddNewTaskDiscount(400,300,bapers,f,acc_no);
            openDialog(addNewTaskDiscount, "Add Task Discount");
    }//GEN-LAST:event_UpdateDiscountButtonActionPerformed

    //updating the task
    private void UpdateDiscountButtonActionPerformed(java.awt.event.ActionEvent evt, JTable table, int acc_no) {
        if (Task_Table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an entry to edit!",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }
        else{
            int column = 1;
            int row = Task_Table.getSelectedRow();
            String catalog_name = table.getModel().getValueAt(row, column).toString();
            AddTaskDiscount addTaskDiscount = new AddTaskDiscount(400,300,bapers,f,acc_no,catalog_name);
            openDialog(addTaskDiscount, "Update Task Discount");
            RefreshButtonActionPerformed(evt, table, acc_no);
        }
    }

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt, JTable table, int customer_acc_no) {//GEN-FIRST:event_DeleteBoundButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        ArrayList<model.discounts.variable_discount.VariableDiscount> discounts = bapers.getVariableDiscount(customer_acc_no);
        for( model.discounts.variable_discount.VariableDiscount variable : discounts){
            model.addRow(new Object[]{variable.getCatalog_ID(), variable.getTask_name(), variable.getDiscount_rate()});
        }
        table.setModel(model);
    }

    private void populateTable(JTable table, int customer_acc_no){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        ArrayList<model.discounts.variable_discount.VariableDiscount> discounts = bapers.getVariableDiscount(customer_acc_no);
        for( model.discounts.variable_discount.VariableDiscount variable : discounts){
            model.addRow(new Object[]{variable.getCatalog_ID(), variable.getTask_name(), variable.getDiscount_rate()});
        }
        table.setModel(model);
    }

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt, JTable table, int acc_no) {//GEN-FIRST:event_DeleteButtonActionPerformed
        try
        {
            int column = 0;
            int row = table.getSelectedRow();
            int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
            bapers.removeVariableDiscount(id, acc_no);
            RefreshButtonActionPerformed(evt, table, acc_no);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a task to remove.",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void openDialog(JPanel panel, String title) {
        final JDialog frame = new JDialog(f, title, true);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    class backListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            setVisible(false);
            f.setLastPanel(lastPanel);
            f.getLastPanel().setVisible(true);
            f.remove(f.getCurrentPanel());
            f.setCurrentPanel(lastPanel);
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JTable Task_Table;
    private javax.swing.JButton UpdateDiscountButton;
    private javax.swing.JButton AddDiscountButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
