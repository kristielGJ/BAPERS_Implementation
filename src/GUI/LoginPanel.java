package GUI;

import model.database.Controller;
import model.database.I_Bapers;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author msy
 */
public class LoginPanel extends javax.swing.JPanel {
    I_Bapers controller;

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel(int width, int height, I_Bapers controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        signInButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        signInLabel = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        idSpinner = new javax.swing.JSpinner();

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emailLabel.setText("ID:");


        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passwordLabel.setText("Password:");

        signInButton.setText("Sign In");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionListener(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("BAPERS");

        signInLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        signInLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        signInLabel.setText("Sign in");

        subtitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        subtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitle.setText("Photographic Labratory");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(112, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(subtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(idSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(passwordLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(passwordInput))
                                        .addComponent(signInButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(signInLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtitle)
                                .addGap(18, 18, 18)
                                .addComponent(signInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140))
        );
    }

    private void signInButtonActionListener(ActionEvent evt) {
        boolean authenticated = controller.authenticateUser((int) idSpinner.getValue(), new String(passwordInput.getPassword()));
        if (authenticated) {
            System.out.println("Authentication success for " + controller.getCurrentUser().toString());
        }else{
            JOptionPane.showMessageDialog(
                    this,
                    "We could not find a password matching to that ID!",
                    "BAPERS", JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private javax.swing.JLabel emailLabel;
    private javax.swing.JSpinner idSpinner;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel signInLabel;
    private javax.swing.JLabel subtitle;
    private javax.swing.JLabel title;
}