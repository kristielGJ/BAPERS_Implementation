package GUI;
import model.Utils;
import model.customers.Customer;
import model.customers.transaction.CustomersTransaction;
import model.database.Controller;
import model.database.DB_Connection;
import model.database.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


public class CreateCustomerAccount extends JPanel {

	private JButton save_button;
	private JTextField customer_name_input;
	private JTextField acc_no;
	private JTextField address_input;
	private JTextField phone_input;
	private JLabel custLabel;
	private JLabel accLabel;
	private JLabel addLabel;
	private JLabel phoneLabel;
	private JLabel custAccLabel;
	private JButton backButton;
	private JButton logOutButton;
	private CustomersTransaction cT = new CustomersTransaction(new DB_Connection());
	private GUI f;
	private JPanel lastPanel;

	public CreateCustomerAccount(int width, int height, GUI f) {
		setSize(width, height);
		setName("Create Customer Account");
		setBackground(new Color(157, 195, 230));
		setLayout(null);
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setCurrentPanel(this);

		save_button = new JButton();
		save_button.setFont(new Font("Tahoma", Font.BOLD, 16));
		save_button.setForeground(Color.white);
		save_button.setBackground(new Color(1, 23, 71));
		save_button.setText("Register Customer");
		save_button.addMouseListener(new saveListener());
		save_button.setBounds((width / 2 - (width / 8)), (height / 2 + height / 5), width / 4, height / 15);
		add(save_button);

		customer_name_input = new JTextField();
		customer_name_input.setBounds((width / 2) + (width / 16), height / 4, width / 5, height / 15);
		add(customer_name_input);

		acc_no = new JTextField();
		acc_no.setEditable(false);
		acc_no.setText(Integer.toString(cT.getLastAccNo() + 1));
		acc_no.setBounds((width / 2) + (width / 16), height / 4 + height / 12, width / 5, height / 15);
		add(acc_no);

		address_input = new JTextField();
		address_input.setBounds((width / 2) + (width / 16), height / 4 + height / 6, width / 5, height / 15);
		add(address_input);

		phone_input = new JTextField();
		phone_input.setBounds((width / 2) + (width / 16), height / 2, width / 5, height / 15);
		add(phone_input);

		custLabel = new JLabel();
		custLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		custLabel.setText("Customer Name");
		custLabel.setBounds((width / 2) - (width / 4), height / 4, width / 5, height / 15);
		add(custLabel);

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		accLabel.setText("Account Number");
		accLabel.setBounds((width / 2) - (width / 4), height / 4 + height / 12, width / 5, height / 15);
		add(accLabel);

		addLabel = new JLabel();
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		addLabel.setText("Customer Address");
		addLabel.setBounds((width / 2) - (width / 4), height / 4 + height / 6, width / 5, height / 15);
		add(addLabel);

		phoneLabel = new JLabel();
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneLabel.setText("Customer Phone Number");
		phoneLabel.setBounds((width / 2) - (width / 4), height / 2, width / 4 + 50, height / 15);
		add(phoneLabel);

		custAccLabel = new JLabel();
		custAccLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		custAccLabel.setForeground(new Color(1, 23, 71));
		custAccLabel.setText("Create Customer Account");
		custAccLabel.setHorizontalAlignment(JLabel.CENTER);
		custAccLabel.setBounds(0, 0, width, height / 4);
		add(custAccLabel);


		backButton = new JButton();
		backButton.setText("Menu");
		backButton.setBounds(width - (width / 7), 0, width / 8, height / 15);
		backButton.addMouseListener(new backListener());
		add(backButton);
	}


	class saveListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!customer_name_input.getText().isEmpty() && !acc_no.getText().isEmpty() && !address_input.getText().isEmpty() && !phone_input.getText().isEmpty()) {
				try {
					cT.addCustomer(customer_name_input.getText(),address_input.getText(),phone_input.getText());
				} catch (Exception eX) {
					eX.printStackTrace();
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