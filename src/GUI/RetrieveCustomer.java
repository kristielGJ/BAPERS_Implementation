package GUI;
import com.google.protobuf.Value;
import model.customers.Customer;
import model.customers.transaction.CustomersTransaction;
import model.database.DB_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RetrieveCustomer extends JPanel {

	private String Valued;
	private Customer foundCustomer;
	private JLabel accLabel, retrieveLabel;
	private JTextArea acc_no;
	private JButton backButton, retrieveButton;
	private final CustomersTransaction cT = new CustomersTransaction(new DB_Connection());
	private GUI f;
	private JPanel lastPanel;

	public RetrieveCustomer(int width, int height, GUI f) {
		this.f = f;
		this.lastPanel = f.getCurrentPanel();
		f.setLastPanel(lastPanel);
		f.setCurrentPanel(this);

		setSize(width, height);
		setName("Customer Details");
		setBackground(new Color(157, 195, 230));
		setLayout(null);

		retrieveButton = new JButton();
		retrieveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		retrieveButton.setForeground(Color.white);
		retrieveButton.setBackground(new Color(1, 23, 71));
		retrieveButton.setText("Retrieve");
		retrieveButton.addMouseListener(new retrieveListener());
		retrieveButton.setBounds((width / 2) - 70, (height / 2 + height / 5), 139, 29);
		add(retrieveButton);

		acc_no = new JTextArea();
		acc_no.setBounds((width / 2), height / 4 + height / 12, width / 4, height / 15);
		acc_no.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(acc_no);

		accLabel = new JLabel();
		accLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		accLabel.setText("Account No:");
		accLabel.setBounds((width / 2) - (width / 4), height / 4 + height / 12, width / 4, height / 15);
		add(accLabel);

		retrieveLabel = new JLabel();
		retrieveLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		retrieveLabel.setForeground(new Color(1, 23, 71));
		retrieveLabel.setText("Retrieve Customer");
		retrieveLabel.setHorizontalTextPosition(JLabel.CENTER);
		retrieveLabel.setBounds((width / 2) - (width/6), 0, (width / 3) * 2, height / 4);
		add(retrieveLabel);

		backButton = new JButton();
		backButton.setBounds(width - (width / 5), 0, width / 6, height / 15);
		backButton.setText("Back");
		backButton.addMouseListener(new backListener());
		add(backButton);
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

	class retrieveListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			foundCustomer = cT.read(Integer.parseInt(acc_no.getText()));
			String[] customerData = {foundCustomer.getCustomer_name(),Integer.toString(foundCustomer.getAccNo()), foundCustomer.getAddress(), foundCustomer.getPhone(), foundCustomer.getValued()};
			if(customerData[4].equals("Valued")){
				f.valuedCustomer(customerData);
			} else {
				f.regularCustomer(customerData);
			}
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