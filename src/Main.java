import GUI.CreateCustomerAccount;
import GUI.GUI;
import GUI.RegularCustomer;
import GUI.UpdateCustomerDetails;
import model.Model;
import model.admin.alert.Alert;
import model.admin.alert.transaction.AlertTransaction;
import model.admin.userAccount.UserAccount;
import model.customers.Customer;
import model.customers.transaction.CustomersTransaction;
import model.database.DB_Connection;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GUI f = new GUI(800,500);
        String[] cD = {"a","4","3","4"};
        f.regularCustomer(cD);
        f.setVisible(true);
    }


}
