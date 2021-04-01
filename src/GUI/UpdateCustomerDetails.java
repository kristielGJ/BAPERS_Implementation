package GUI;

import model.database.I_Bapers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

/**
 *
 * @author Jacob
 */

public class UpdateCustomerDetails extends JPanel {

	//private global variables in UpdateCustomerAccount
	private String customerName, accNo, CustomerAddress, CustomerPhone, company,valued;
	private JTextArea customer_name_input, acc_no, company_input, address_input, phone_input;
	private JLabel custLabel, accLabel, addLabel, phoneLabel, companyLabel, custDetLabel, miniBapersLabel;
	private JButton updateButton,backButton, menuButton;
	private JCheckBox valuedCustomerBox;
	private I_Bapers cT;
	private GUI f;
	private JPanel lastPanel;

	//constructor for UpdateCustomerDetails, initialises global variables and sets layout for frame
	public UpdateCustomerDetails(int width, int height, String[] customerData, GUI f, I_Bapers cT) {
		this.customerName = customerData[0];
		this.accNo = customerData[1];
		this.CustomerAddress = customerData[2];
		this.CustomerPhone = customerData[3];
		this.valued = customerData[4];
		this.company = customerData[5];
		this.f = f;
		this.cT= cT;
		this.lastPanel = f.getCurrentPanel();
		f.setCurrentPanel(this);

		setSize(width, height);
		setName("Update Customer Details");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		//Initialisation of components for JPanel
		updateButton = new JButton();
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setForeground(Color.white);
		updateButton.setBackground(new Color(1, 23, 71));
		updateButton.setText("Update Details");
		updateButton.addMouseListener(new updateListener());

		customer_name_input = new JTextArea();
		customer_name_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customer_name_input.setText(customerName);

		company_input = new JTextArea();
		company_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		company_input.setText(company);

		acc_no = new JTextArea();
		acc_no.setEditable(false);
		acc_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acc_no.setText(accNo);

		address_input = new JTextArea();
		address_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address_input.setText(CustomerAddress);

		phone_input = new JTextArea();
		phone_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone_input.setText(CustomerPhone);

		custLabel = new JLabel();
		custLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		custLabel.setText("Name:");

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		accLabel.setText("Account No:");

		addLabel = new JLabel();
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addLabel.setText("Address:");

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		phoneLabel.setText("Phone:");

		companyLabel = new JLabel();
		companyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		companyLabel.setText("Phone:");

		valuedCustomerBox = new JCheckBox("Valued Customer?");
		if(valued.equals("Valued")){
			valuedCustomerBox.setSelected(true);
		}
		valuedCustomerBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		valuedCustomerBox.setOpaque(false);
		valuedCustomerBox.setHorizontalAlignment(JCheckBox.CENTER);

		custDetLabel = new JLabel();
		custDetLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		custDetLabel.setForeground(new Color(1, 23, 71));
		custDetLabel.setText("Update Details");
		custDetLabel.setHorizontalTextPosition(JLabel.CENTER);

		backButton = new JButton();
		backButton.setText("Back");
		backButton.setForeground(Color.white);
		backButton.setBackground(new Color(1, 23, 71));
		backButton.addMouseListener(new backListener());

		menuButton = new JButton();
		menuButton.setText("Menu");
		menuButton.setForeground(Color.white);
		menuButton.setBackground(new Color(1, 23, 71));
		menuButton.addMouseListener(new menuListener());

		miniBapersLabel = new JLabel();
		miniBapersLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		miniBapersLabel.setForeground(new java.awt.Color(1, 23, 71));
		miniBapersLabel.setText("Bapers");


		//layout manager for JPanel
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(150,150,240)
								.addComponent(custDetLabel)
								.addGap(159, 159, 1000))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(backButton)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(menuButton)
								.addGap(19, 19, 19))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(65, 65, 1000)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(phoneLabel)
														.addComponent(addLabel)
														.addComponent(accLabel)
														.addComponent(companyLabel)
														.addComponent(custLabel))
												.addGap(29, 29, 29)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(acc_no)
														.addComponent(phone_input)
														.addComponent(address_input)
														.addComponent(company_input, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
														.addComponent(customer_name_input, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(345,345,345)
												.addComponent(valuedCustomerBox))
										.addGroup(layout.createSequentialGroup()
												.addGap(250,250,250)
												.addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(379, 379, 379)
												.addComponent(miniBapersLabel)))
								.addContainerGap(70, 1000))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(45, 45, 45)
												.addComponent(custDetLabel))
										.addGroup(layout.createSequentialGroup()
												.addGap(19, 19, 19)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(menuButton)
														.addComponent(backButton))))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(miniBapersLabel)
												.addContainerGap())
										.addGroup(layout.createSequentialGroup()
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(custLabel)
														.addComponent(customer_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(companyLabel)
														.addComponent(company_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(accLabel)
														.addComponent(acc_no, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(addLabel)
														.addComponent(address_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(phoneLabel)
														.addComponent(phone_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(valuedCustomerBox))
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(updateButton))
												.addGap(55, 55, 55))))
		);


	}

	//mouse listener for update button, updates customer info to database
	class updateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			String valued;
			if(valuedCustomerBox.isSelected()){
				valued = "Valued";
			} else {
				valued = "Regular";
			}
			if(!customer_name_input.getText().isEmpty() && !address_input.getText().isEmpty() && !phone_input.getText().isEmpty()) {
				try {
					cT.updateCustomerDetails(customer_name_input.getText(),company_input.getText(), Integer.parseInt(accNo), address_input.getText(), phone_input.getText(), valued);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(f.getCurrentPanel(), "Please fill all entries", "BAPERS", JOptionPane.ERROR_MESSAGE);
			}

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

	//mouse listener for back button, returns to previous panel
	class backListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
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

	//mouse listener for menu button, returns to menu button
	class menuListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			f.home();
			setVisible(false);
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
}

