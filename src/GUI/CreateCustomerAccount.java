package GUI;
import database.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


public class CreateCustomerAccount extends JPanel {

	private JButton save_button;
	private JTextField customer_name_input;
	private JTextField acc_no_input;
	private JTextField address_input;
	private JTextField phone_input;
	private JLabel custLabel;
	private JLabel accLabel;
	private JLabel addLabel;
	private JLabel phoneLabel;
	private JLabel custAccLabel;
	private JComboBox menuBox;


	public CreateCustomerAccount(int width, int height) {
		setSize(width, height);
		setName("Create Customer Account");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		save_button = new JButton();
		save_button.setFont(new Font("Ariel", Font.BOLD, 18));
		save_button.setForeground(Color.white);
		save_button.setBackground(new Color(1, 23, 71));
		save_button.setText("Register Customer");
		save_button.addMouseListener(new saveListener());
		save_button.setBounds((width / 2 - (width / 8)), (height / 2 + height / 5), width / 4, height / 15);
		add(save_button);

		customer_name_input = new JTextField();
		customer_name_input.setBounds((width / 2) + (width / 16), height / 4, width / 5, height / 15);
		add(customer_name_input);

		acc_no_input = new JTextField();
		acc_no_input.setBounds((width / 2) + (width / 16), height / 4 + height / 12, width / 5, height / 15);
		add(acc_no_input);

		address_input = new JTextField();
		address_input.setBounds((width / 2) + (width / 16), height / 4 + height / 6, width / 5, height / 15);
		add(address_input);

		phone_input = new JTextField();
		phone_input.setBounds((width / 2) + (width / 16), height / 2, width / 5, height / 15);
		add(phone_input);

		custLabel = new JLabel();
		custLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		custLabel.setText("Customer Name");
		custLabel.setBounds((width / 2) - (width / 4), height / 4, width / 5, height / 15);
		add(custLabel);

		accLabel = new JLabel();
		accLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		accLabel.setText("Account Number");
		accLabel.setBounds((width / 2) - (width / 4), height / 4 + height / 12, width / 5, height / 15);
		add(accLabel);

		addLabel = new JLabel();
		addLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		addLabel.setText("Customer Address");
		addLabel.setBounds((width / 2) - (width / 4), height / 4 + height / 6, width / 5, height / 15);
		add(addLabel);

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		phoneLabel.setText("Customer Phone Number");
		phoneLabel.setBounds((width / 2) - (width / 4), height / 2, width / 4, height / 15);
		add(phoneLabel);

		custAccLabel = new JLabel();
		custAccLabel.setFont(new Font("Ariel", Font.BOLD, 30));
		custAccLabel.setForeground(new Color(1, 23, 71));
		custAccLabel.setText("Customer Account");
		custAccLabel.setHorizontalTextPosition(JLabel.CENTER);
		custAccLabel.setBounds((width / 2) - (width / 6), 0, width / 2, height / 4);
		add(custAccLabel);


		String[] menuS = {"Menu", "Go Back", "Log Out"};
		menuBox = new JComboBox(menuS);
		menuBox.setBounds(width - (width / 7), 0, width / 8, height / 15);
		add(menuBox);
	}


	class saveListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!customer_name_input.getText().isEmpty() && !acc_no_input.getText().isEmpty() && !address_input.getText().isEmpty() && !phone_input.getText().isEmpty()) {
				String[] customerData = {customer_name_input.getText(), acc_no_input.getText(), address_input.getText(), phone_input.getText()};
				try {
					Controller cL = new Controller();
					cL.createNewCustomer(customerData);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
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
}