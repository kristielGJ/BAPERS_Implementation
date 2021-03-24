package GUI;
import customers.Customer;
import database.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class RegularCustomer extends JPanel {

	private String customerName, accNo, CustomerAddress, CustomerPhone;
	private JLabel customer_name, acc_no, address, phone, custLabel, accLabel, addLabel, phoneLabel, custDetLabel;
	private JComboBox menuBox;
	private JButton updateButton, addJobButton;


	public RegularCustomer(int width, int height, String[] customerData) {
		this.customerName = customerData[0];
		this.accNo = customerData[1];
		this.CustomerAddress = customerData[2];
		this.CustomerPhone = customerData[3];

		setSize(width, height);
		setName("Create Customer Account");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		updateButton = new JButton();
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setForeground(Color.white);
		updateButton.setBackground(new Color(1, 23, 71));
		updateButton.setText("Update Details");
		updateButton.addMouseListener(new updateListener());
		updateButton.setBounds((width / 2) - (width / 16) - 139, (height / 2 + height / 5), 139, 29);
		add(updateButton);

		addJobButton = new JButton();
		addJobButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addJobButton.setForeground(Color.white);
		addJobButton.setBackground(new Color(1, 23, 71));
		addJobButton.setText("Add Job");
		addJobButton.addMouseListener(new addJobListener());
		addJobButton.setBounds((width / 2) + (width / 16), (height / 2 + height / 5), 130, 29);
		add(addJobButton);

		customer_name = new JLabel();
		customer_name.setBounds((width / 10) + (width / 4), height / 4, width / 2, height / 15);
		customer_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customer_name.setText(customerName);
		add(customer_name);

		acc_no = new JLabel();
		acc_no.setBounds((width / 10) + (width / 4), height / 4 + height / 12, width / 2, height / 15);
		acc_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acc_no.setText(accNo);
		add(acc_no);

		address = new JLabel();
		address.setBounds((width / 10) + (width / 4), height / 4 + height / 6, width / 2, height / 15);
		address.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address.setText(CustomerAddress);
		add(address);

		phone = new JLabel();
		phone.setBounds((width / 10) + (width / 4), height / 2, width / 2, height / 15);
		phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone.setText(CustomerPhone);
		add(phone);

		custLabel = new JLabel();
		custLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		custLabel.setText("Name:");
		custLabel.setBounds((width/10), height / 4, width / 5, height / 15);
		add(custLabel);

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		accLabel.setText("Account No:");
		accLabel.setBounds((width / 10), height / 4 + height / 12, width / 4, height / 15);
		add(accLabel);

		addLabel = new JLabel();
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addLabel.setText("Address:");
		addLabel.setBounds((width / 10), height / 4 + height / 6, width / 5, height / 15);
		add(addLabel);

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		phoneLabel.setText("Phone:");
		phoneLabel.setBounds((width / 10), height / 2, width / 4 + 50, height / 15);
		add(phoneLabel);

		custDetLabel = new JLabel();
		custDetLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		custDetLabel.setForeground(new Color(1, 23, 71));
		custDetLabel.setText("Customer Details");
		custDetLabel.setHorizontalTextPosition(JLabel.CENTER);
		custDetLabel.setBounds((width / 2) - (width / 3), 0, (width / 3) * 2, height / 4);
		add(custDetLabel);


		String[] menuS = {"Menu", "Go Back", "Log Out"};
		menuBox = new JComboBox(menuS);
		menuBox.setBounds(width - (width / 5), 0, width / 6, height / 15);
		add(menuBox);
	}


	class updateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

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

	class addJobListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {

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

