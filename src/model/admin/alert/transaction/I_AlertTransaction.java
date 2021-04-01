package model.admin.alert.transaction;

import model.Transaction;
import model.admin.alert.Alert;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface I_AlertTransaction extends Transaction {
    Alert create(String name, String message, LocalDateTime time, int jobId);
    Alert update(int id, String name, String message, LocalDateTime time, int jobId);
    ArrayList<Alert> getAll();
}
