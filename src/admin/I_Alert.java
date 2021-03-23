package admin;

import java.sql.ResultSet;
import java.time.LocalDateTime;

public interface I_Alert {

    void createAlert(String name, String message, LocalDateTime time, int jobId);
    ResultSet getAllAlerts();
    ResultSet retrieveAlert(int id);
    void removeAlert(int id);

}
