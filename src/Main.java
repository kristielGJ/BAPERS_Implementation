import GUI.GUI;
import model.Model;
import model.admin.alert.Alert;
import model.admin.alert.transaction.AlertTransaction;
import model.admin.userAccount.UserAccount;
import model.database.DB_Connection;
import model.discounts.Discount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GUI f = new GUI(800,500);
        String[] cD = {"2", "3","4","5"};
        f.regularCustomer(cD);
        f.setVisible(true);
    }


}
