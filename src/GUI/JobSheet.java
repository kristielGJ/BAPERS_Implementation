/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import model.database.I_Bapers;
import reports.job_performance_report.JobPerformanceReport;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author kehkshan and Manpreet
 */

public class JobSheet extends javax.swing.JPanel {

    private I_Bapers bapers;
    private GUI f;
    private JPanel lastPanel;

    /**
     * Creates new form JobSheet
     */
    public JobSheet(int width, int height, LocalDate from_date, LocalDate to_date,  int customer_id, I_Bapers bapers, GUI f) {
        initComponents(width, height, from_date, to_date, customer_id, bapers, f);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(int width, int height, LocalDate from_date, LocalDate to_date, int customer_id, I_Bapers bapers, GUI f) {

        this.f = f;
        this.lastPanel = f.getCurrentPanel();
        f.setCurrentPanel(this);
        this.bapers = bapers;
        print_button = new javax.swing.JButton();
        back_button = new javax.swing.JButton();
        job_sheet = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        customer_input = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        job_sheet_table_input = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        customer_input.setEditable(false);
        jTextField1.setEditable(false);

        setBackground(new java.awt.Color(157, 195, 230));
        setMaximumSize(new java.awt.Dimension(width, height));
        setPreferredSize(new java.awt.Dimension(width, height));

        print_button.setBackground(new java.awt.Color(1, 23, 71));
        print_button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        print_button.setForeground(new java.awt.Color(157, 195, 230));
        print_button.setText("Print");
        print_button.setBorderPainted(false);
        print_button.setFocusPainted(false);
        print_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f.printPanel(job_sheet, "Print Job Sheet");
            }
        });

        back_button.setBackground(new java.awt.Color(1, 23, 71));
        back_button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        back_button.setForeground(new java.awt.Color(157, 195, 230));
        back_button.setText("Back");
        back_button.setBorderPainted(false);
        back_button.setFocusPainted(false);
        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                back_buttonMouseClicked(evt);
            }
        });

        job_sheet.setBackground(new java.awt.Color(157, 195, 230));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 23, 71));
        jLabel3.setText("Customer:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 23, 71));
        jLabel4.setText("Date:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 23, 71));
        jLabel1.setText("Job Sheet");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 23, 71));
        jLabel7.setText("Description of work in progress:");

        job_sheet_table_input.setModel(new javax.swing.table.DefaultTableModel(
            new String [] {
                "Job", "Price", "Task", "Department", "Start Time", "Time Taken", "Compeleted by"
            },0
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addJobSheet(job_sheet_table_input, from_date, to_date, customer_id);
        job_sheet_table_input.setGridColor(new java.awt.Color(1, 23, 71));
        job_sheet_table_input.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(job_sheet_table_input);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 23, 71));
        jLabel2.setText("Bapers");

        javax.swing.GroupLayout job_sheetLayout = new javax.swing.GroupLayout(job_sheet);
        job_sheet.setLayout(job_sheetLayout);
        job_sheetLayout.setHorizontalGroup(
            job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(job_sheetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(job_sheetLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, job_sheetLayout.createSequentialGroup()
                        .addGroup(job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(job_sheetLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(customer_input, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, job_sheetLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(367, 367, 367))
        );
        job_sheetLayout.setVerticalGroup(
            job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, job_sheetLayout.createSequentialGroup()
                .addGroup(job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(job_sheetLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(job_sheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(print_button, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(job_sheet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(print_button))
                .addComponent(job_sheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void addJobSheet(JTable table, LocalDate from_date, LocalDate to_date, int customer_id) {
        LocalDate date = LocalDate.now();
        customer_input.setText(String.valueOf(customer_id));
        jTextField1.setText(String.valueOf(date));
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        ArrayList<JobPerformanceReport> details = bapers.generateJobSheet(from_date, to_date, customer_id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(JobPerformanceReport report : details){
            model.addRow(new Object[]{report.getJob_id(), report.getPrice(), report.getTask_id(), report.getLocation(), report.getStart_time().format(formatter), report.getTime_taken(), report.getTechnician()});
        }
        table.setModel(model);
    }

    private void back_buttonMouseClicked(java.awt.event.MouseEvent evt) {
        f.setLastPanel(lastPanel);
        f.getLastPanel().setVisible(true);
        f.remove(f.getCurrentPanel());
        f.setCurrentPanel(lastPanel);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JTextField customer_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel job_sheet;
    private javax.swing.JTable job_sheet_table_input;
    private javax.swing.JButton print_button;
    // End of variables declaration//GEN-END:variables
}
