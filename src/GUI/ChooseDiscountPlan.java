/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.I_Bapers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mushfikur
 */
public class ChooseDiscountPlan extends javax.swing.JPanel {

    private int width;
    private int height;
    private I_Bapers bapers;
    private GUI f;
    private int acc_no;

    /**
     * Creates new form ChooseDiscountPlan
     */
    public ChooseDiscountPlan(int width, int height, I_Bapers bapers, GUI f, int acc_no) {
        this.width = width;
        this.height = height;
        this.bapers = bapers;
        this.f = f;
        this.acc_no = acc_no;
        initComponents();
    }

    private void initComponents() {
        pageTitle = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        discountPlanType = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(157, 195, 230));
        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pageTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pageTitle.setForeground(new java.awt.Color(1, 23, 71));
        pageTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageTitle.setText("Choose Discount Plan");

        applyButton.setBackground(new java.awt.Color(1, 23, 71));
        applyButton.setForeground(new java.awt.Color(157, 195, 230));
        applyButton.setText("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyButtonActionPerformed(e);
            }
        });

        discountPlanType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Discount Plan", "Fixed", "Variable", "Flexible", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(discountPlanType, 0, 278, Short.MAX_VALUE)
                                        .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(discountPlanType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(applyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103))
        );
    }

    private void applyButtonActionPerformed(ActionEvent e) {
        String typeSelected = (String) discountPlanType.getSelectedItem();
        bapers.updateDiscountPlan(acc_no, typeSelected);
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }


    private javax.swing.JButton applyButton;
    private javax.swing.JComboBox<String> discountPlanType;
    private javax.swing.JLabel pageTitle;
}
