package GUI;

import model.database.Controller;
import model.database.I_Bapers;

import javax.swing.*;
import java.awt.*;
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
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        passwordInput = new JPasswordField();
        signInButton = new JButton();
        title = new JLabel();
        signInLabel = new JLabel();
        subtitle = new JLabel();
        idSpinner = new JSpinner();

        emailLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        emailLabel.setText("ID:");

        passwordLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        passwordLabel.setText("Password:");

        signInButton.setText("Sign In");

        title.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("BAPERS");

        signInLabel.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        signInLabel.setHorizontalAlignment(SwingConstants.LEFT);
        signInLabel.setText("Sign in");

        subtitle.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setText("Photographic Labratory");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(114, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                .addGap(525, 525, 525))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(subtitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(idSpinner, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(signInButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(passwordLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(passwordInput))
                                                .addComponent(signInLabel, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)))
                                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(title)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtitle)
                                .addGap(18, 18, 18)
                                .addComponent(signInLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(idSpinner, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(165, 165, 165))
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