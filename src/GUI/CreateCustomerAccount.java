package GUI;
import model.database.I_Bapers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CreateCustomerAccount extends JPanel {

	//private global variables in CreateCustomerAccount
	private JButton save_button;
	private JTextField customer_name_input, acc_no, address_input, phone_input;
	private JLabel custLabel, accLabel, addLabel, phoneLabel, custAccLabel, miniBapersLabel;
	private JButton backButton, menuButton;
	private I_Bapers cT;
	private GUI f;
	private JPanel lastPanel;


	//constructor for CreateCustomer, initialises global variables and sets layout for frame
	public CreateCustomerAccount(int width, int height, GUI f, I_Bapers cT) {
		setSize(width, height);
		setName("Create Customer Account");
		setBackground(new Color(157, 195, 230));
		setLayout(null);
		this.cT = cT;
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setCurrentPanel(this);

		//Initialisation of components for JPanel
		save_button = new JButton();
		save_button.setFont(new Font("Tahoma", Font.BOLD, 16));
		save_button.setForeground(Color.white);
		save_button.setBackground(new Color(1, 23, 71));
		save_button.setText("Register Customer");
		save_button.addMouseListener(new saveListener());

		customer_name_input = new JTextField();

		acc_no = new JTextField();
		acc_no.setEditable(false);
		acc_no.setText(Integer.toString(cT.getLastAccNo() + 1));

		address_input = new JTextField();

		phone_input = new JTextField();

		custLabel = new JLabel();
		custLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		custLabel.setText("Customer Name");

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		accLabel.setText("Account Number");

		addLabel = new JLabel();
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		addLabel.setText("Customer Address");

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneLabel.setText("Customer Phone Number");

		custAccLabel = new JLabel();
		custAccLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		custAccLabel.setForeground(new Color(1, 23, 71));
		custAccLabel.setText("Create Customer Account");
		custAccLabel.setHorizontalAlignment(JLabel.CENTER);

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

		//layout manager for panel
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(150,150,240)
								.addComponent(custAccLabel)
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
												.addGap(122, 122, 1000)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(phoneLabel)
														.addComponent(addLabel)
														.addComponent(accLabel)
														.addComponent(custLabel))
												.addGap(29, 29, 29)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(acc_no)
														.addComponent(phone_input)
														.addComponent(address_input, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
														.addComponent(customer_name_input, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(250,250,250)
												.addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
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
												.addComponent(custAccLabel))
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
														.addComponent(save_button))
												.addGap(55, 55, 55))))
		);

	}

	//mouse listener for save button, adds new customer too database
	class saveListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!customer_name_input.getText().isEmpty() && !acc_no.getText().isEmpty() && !address_input.getText().isEmpty() && !phone_input.getText().isEmpty()) {
				try {
					cT.createNewCustomer(customer_name_input.getText(),address_input.getText(),phone_input.getText());
				} catch (Exception eX) {
					eX.printStackTrace();
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