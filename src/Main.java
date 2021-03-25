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
        Discount discount_test = null;
        discount_test.addDiscount(2.0,"fixed",1);

    }


}
