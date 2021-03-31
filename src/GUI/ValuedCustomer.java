package GUI;
import model.customers.transaction.CustomersTransaction;
import model.database.DB_Connection;
import model.database.I_Bapers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ValuedCustomer extends JPanel {


	//defining global variables
	private String customerName, accNo, CustomerAddress, CustomerPhone;
	private JLabel customer_name, acc_no, address, phone, custLabel, accLabel, addLabel, phoneLabel, custDetLabel, miniBapersLabel;
	private JButton updateButton, addJobButton, retrieveJob, backButton, discountPlanButton, menuButton, createDiscountButton;
	private GUI f;
	private JPanel lastPanel;
	private I_Bapers bapers;


	//constructor for panel, initialise components for panel, initalise global variables and add layout manager
	public ValuedCustomer(int width, int height, String[] customerData,I_Bapers bapers, GUI f) {
		this.customerName = customerData[0];
		this.accNo = customerData[1];
		this.CustomerAddress = customerData[2];
		this.CustomerPhone = customerData[3];
		this.bapers = bapers;
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setLastPanel(lastPanel);
		f.setCurrentPanel(this);

		setSize(width, height);
		setName("Customer Details");
		setBackground(new Color(157, 195, 230));


		//initialise components for panel
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

		discountPlanButton = new JButton();
		discountPlanButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		discountPlanButton.setForeground(Color.white);
		discountPlanButton.setBackground(new Color(1, 23, 71));
		discountPlanButton.setText("Manage Discount");
		discountPlanButton.addMouseListener(new DiscountListener());

		createDiscountButton = new JButton();
		createDiscountButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createDiscountButton.setForeground(Color.white);
		createDiscountButton.setBackground(new Color(1, 23, 71));
		createDiscountButton.setText("Choose Discount");
		createDiscountButton.addMouseListener(new createDiscountListener());

		customer_name = new JLabel();
		customer_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customer_name.setText(customerName);

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
		backButton.setForeground(Color.white);
		backButton.setBackground(new Color(1, 23, 71));
		backButton.setText("Back");
		backButton.addMouseListener(new backListener());

		retrieveJob = new JButton();
		retrieveJob.setForeground(Color.white);
		retrieveJob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		retrieveJob.setBackground(new Color(1, 23, 71));
		retrieveJob.setText("Retrieve Job");
		retrieveJob.addMouseListener(new retrieveListener());

		menuButton = new JButton();
		menuButton.setForeground(Color.white);
		menuButton.setBackground(new Color(1, 23, 71));
		menuButton.setText("Menu");
		menuButton.addMouseListener(new menuListener());

		miniBapersLabel = new JLabel();
		miniBapersLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		miniBapersLabel.setForeground(new java.awt.Color(1, 23, 71));
		miniBapersLabel.setText("Bapers");

		//create layout manager for panel
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(105,105,240)
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
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(phoneLabel)
														.addComponent(addLabel)
														.addComponent(accLabel)
														.addComponent(custLabel))
												.addGap(29, 29, 29)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(acc_no)
														.addComponent(phone)
														.addComponent(address, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
														.addComponent(customer_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(30, 30, 30)
												.addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(46, 46, 46)
												.addComponent(retrieveJob, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(46, 46, 46)
												.addComponent(addJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(46, 46, 46)
												.addGroup(layout.createParallelGroup()
												.addComponent(discountPlanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(createDiscountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
														.addComponent(customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
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
														.addComponent(retrieveJob)
														.addComponent(discountPlanButton))
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addGap(10,10,10)
														.addComponent(createDiscountButton))
												.addGap(55, 55, 55))))
		);
		}

		//mouse listener for back button, will send GUI to last used panel
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

		//mouse listener for update button, will send GUI to update customer Panel
		class updateListener implements MouseListener {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] cD = {customerName,accNo,CustomerAddress,CustomerPhone,"Valued"};
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

		//mouse listener for add job button, will send GUI to add Job Panel
		class addJobListener implements MouseListener {
			@Override
			public void mouseClicked(MouseEvent e) {
				f.addJob(Integer.parseInt(accNo));
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

		//mouse listener for create Discount button, will add a pop up box with the panel CreateDiscountPlan
		class createDiscountListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			PopUpBox pub = new PopUpBox(400,300,"Add Discount");
			pub.add(new CreateDiscountPlan(400,300,bapers,Integer.parseInt(accNo)));
			pub.setVisible(true);

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

		/*
		 mouse listener for manage discount button, will send GUI to Flexible Panel,
		 Variable panel or a new pop up box for Fixed Discount depending on type of discount owned by customer
		*/
		class DiscountListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			String discountType = bapers.getDiscountType(Integer.parseInt(accNo));
			if(discountType.equals("Fixed")){
				PopUpBox pub = new PopUpBox(400,300,"Fixed Discount");
				pub.add(new FixedDiscount(400,300, bapers, Integer.parseInt(accNo)));
				pub.setVisible(true);
			} else if (discountType.equals("Flexible")){
				f.flexibleDiscount(Integer.parseInt(accNo));
				setVisible(false);
			} else if (discountType.equals("Variable")){
				f.variableDiscount(Integer.parseInt(accNo));
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(f.getCurrentPanel(), "No discount applied", "BAPERS", JOptionPane.ERROR_MESSAGE);
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

		//mouse listener for retrieve jobs button, will send gui to retrieveJobs panel
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

		//mouse listener for menu button, will send gui back to menu
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

