import model.Model;
import model.admin.alert.Alert;
import model.admin.alert.transaction.AlertTransaction;
import model.admin.userAccount.UserAccount;
import model.database.DB_Connection;
import model.discount.DiscountHelper;
import model.discount.FixedDiscount;
import model.discount.transaction.DiscountTransaction;
import model.discounts.Discount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DiscountHelper helper = new DiscountHelper(new DB_Connection(), 4);
        helper.applyFixedDiscount(3000);
    }


}
