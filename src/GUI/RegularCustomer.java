package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegularCustomer extends JPanel {

	/**
	 *	private global variables in RegularCustomer
	 */
	private String customerName, accNo, CustomerAddress, CustomerPhone,company, Valued;
	private JLabel customer_name, Cust_company, acc_no, address, phone, custLabel, accLabel, companyLabel, addLabel, phoneLabel, custDetLabel, miniBapersLabel;
	private JButton updateButton, addJobButton, backButton, menuButton, retrieveJob;
	private GUI f;
	private JPanel lastPanel;

	/**
	 * constructor for panel, initialise components for panel, initalise global variables and add layout manager
	 * @param width
	 * @param height
	 * @param customerData
	 * @param f
	 */
	public RegularCustomer(int width, int height, String[] customerData, GUI f) {
		this.customerName = customerData[0];
		this.accNo = customerData[1];
		this.CustomerAddress = customerData[2];
		this.CustomerPhone = customerData[3];
		this.company = customerData[5];
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setLastPanel(lastPanel);
		f.setCurrentPanel(this);

		setSize(width, height);
		setName("Customer Details");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		/**
		 *  initialises components for panel
		 */
		updateButton = new JButton();
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setForeground(Color.white);
		updateButton.setBackground(new Color(1, 23, 71));
		updateButton.setText("Update Details");
		updateButton.addMouseListener(new updateListener());

		addJobButton = new JButton();
		addJobButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addJobButton.setForeground(Color.white);
		addJobButton.setBackground(new Color(1, 23, 71));
		addJobButton.setText("Add Job");
		addJobButton.addMouseListener(new addJobListener());

		customer_name = new JLabel();
		customer_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customer_name.setText(customerName);

		Cust_company = new JLabel();
		Cust_company.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Cust_company.setText(company);

		acc_no = new JLabel();
		acc_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acc_no.setText(accNo);

		address = new JLabel();
		address.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address.setText(CustomerAddress);

		phone = new JLabel();
		phone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phone.setText(CustomerPhone);

		custLabel = new JLabel();
		custLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		custLabel.setText("Name:");

		companyLabel = new JLabel();
		companyLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		companyLabel.setText("Company:");

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		accLabel.setText("Account No:");

		addLabel = new JLabel();
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		addLabel.setText("Address:");

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneLabel.setText("Phone:");

		custDetLabel = new JLabel();
		custDetLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		custDetLabel.setForeground(new Color(1, 23, 71));
		custDetLabel.setText("Customer Details");
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

		retrieveJob = new JButton();
		retrieveJob.setText("Retrieve Job");
		retrieveJob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		retrieveJob.setForeground(Color.white);
		retrieveJob.setBackground(new Color(1, 23, 71));
		retrieveJob.addMouseListener(new retrieveListener());

		miniBapersLabel = new JLabel();
		miniBapersLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		miniBapersLabel.setForeground(new java.awt.Color(1, 23, 71));
		miniBapersLabel.setText("Bapers");


		/**
		 *  layout manager for panel
		 */
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(145,145,240)
								.addComponent(custDetLabel)
								.addGap(145, 145, 1000))
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
														.addComponent(phone)
														.addComponent(address)
														.addComponent(Cust_company, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
														.addComponent(customer_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(46, 46, 46)
												.addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(46, 46, 46)
												.addComponent(retrieveJob, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(46, 46, 46)
												.addComponent(addJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(370, 370, 370)
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
														.addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(companyLabel)
														.addComponent(Cust_company, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(accLabel)
														.addComponent(acc_no, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(addLabel)
														.addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(phoneLabel)
														.addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(updateButton)
														.addComponent(addJobButton)
														.addComponent(retrieveJob))
												.addGap(55, 55, 55))))
		);
	}

	/**
	 mouse listener for back button, returns to previous panel
	 */
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

	/**
	 * mouse listener for update button, will send GUI to update customer Panel
	 */
	class updateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			String[] cD = {customerName,accNo,CustomerAddress,CustomerPhone,"Regular", company};
			f.updateCustomer(cD);
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

	/**
	 * mouse listener for add job button, will send GUI to add Job Panel
	 */
	class addJobListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			f.addJob(Integer.parseInt(acc_no.getText()));
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

	/**
	 * mouse listener for retrieve jobs button, will send gui to retrieveJobs panel
	 */
	class retrieveListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			f.retrieveJobs(Integer.parseInt(accNo));
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

	/**
	 * mouse listener for menu button, returns to menu button
	 */
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

