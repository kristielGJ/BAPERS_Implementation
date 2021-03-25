package GUI;
import model.customers.transaction.CustomersTransaction;
import model.database.DB_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UpdateCustomerDetails extends JPanel {

	private String customerName, accNo, CustomerAddress, CustomerPhone;
	private JTextArea customer_name_input, acc_no, address_input, phone_input;
	private JLabel custLabel, accLabel, addLabel, phoneLabel, custDetLabel;
	private JButton updateButton,backButton;
	private JCheckBox valuedCustomerBox;
	private CustomersTransaction cT = new CustomersTransaction(new DB_Connection());
	private GUI f;
	private JPanel lastPanel;

	public UpdateCustomerDetails(int width, int height, String[] customerData, GUI f) {
		this.customerName = customerData[0];
		this.accNo = customerData[1];
		this.CustomerAddress = customerData[2];
		this.CustomerPhone = customerData[3];
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setCurrentPanel(this);

		setSize(width, height);
		setName("Update Customer Details");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		updateButton = new JButton();
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateButton.setForeground(Color.white);
		updateButton.setBackground(new Color(1, 23, 71));
		updateButton.setText("Update Details");
		updateButton.addMouseListener(new updateListener());
		updateButton.setBounds((width / 2) - (138/2), (height / 2 + height / 5), 139, 29);
		add(updateButton);

		customer_name_input = new JTextArea();
		customer_name_input.setBounds((width / 10) + (width / 4), height / 4, width / 2, height / 15);
		customer_name_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customer_name_input.setText(customerName);
		add(customer_name_input);

		acc_no = new JTextArea();
		acc_no.setEditable(false);
		acc_no.setBounds((width / 10) + (width / 4), height / 4 + height / 12, width / 2, height / 15);
		acc_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
		acc_no.setText(accNo);
		add(acc_no);

		address_input = new JTextArea();
		address_input.setBounds((width / 10) + (width / 4), height / 4 + height / 6, width / 2, height / 15);
		address_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address_input.setText(CustomerAddress);
		add(address_input);

		phone_input = new JTextArea();
		phone_input.setBounds((width / 10) + (width / 4), height / 2, width / 2, height / 15);
		phone_input.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone_input.setText(CustomerPhone);
		add(phone_input);

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

		valuedCustomerBox = new JCheckBox("Valued Customer?");
		valuedCustomerBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(valuedCustomerBox);

		custDetLabel = new JLabel();
		custDetLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		custDetLabel.setForeground(new Color(1, 23, 71));
		custDetLabel.setText("Update Details");
		custDetLabel.setHorizontalAlignment(JLabel.CENTER);
		custDetLabel.setBounds((width / 2) - (width / 3), 0, (width / 3) * 2, height / 4);
		add(custDetLabel);

		backButton = new JButton();
		backButton.setText("Back");
		backButton.addMouseListener(new backListener());
		backButton.setBounds(width - (width / 5), 0, width / 6, height / 15);
		add(backButton);
	}


	class updateListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			String valued;
			if(valuedCustomerBox.isSelected()){
				valued = "Valued";
			} else {
				valued = "Regular";
			}
			cT.updateCustomer(customer_name_input.getText(),Integer.parseInt(accNo),address_input.getText(), phone_input.getText(), valued);
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
}

