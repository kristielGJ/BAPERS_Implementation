import model.Model;
import model.admin.alert.Alert;
import model.admin.alert.transaction.AlertTransaction;
import model.admin.userAccount.UserAccount;
import model.database.DB_Connection;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AlertTransaction alertTransaction = new AlertTransaction(new DB_Connection());
        Alert alert = alertTransaction.create("payment", "TEST2", LocalDateTime.now(), 3);
        alert = alertTransaction.update(alert.getId(), "job", "TEST3", LocalDateTime.now(), 2);
        alert = alertTransaction.read(alert.getId());
        System.out.println(alert.getName());
    }

}
