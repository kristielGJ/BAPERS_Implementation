package model.admin.alert;

import model.database.DB_Connection;
import model.database.I_Bapers;
import model.jobs.job.Job;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledAlert class.
 * Is a model for the actual scheduling of an Alert in the system.
 */

public class ScheduledAlert {

    private Alert alert;
    private I_Bapers controller;
    private boolean isRunning;
    ScheduledFuture<?> alertHandle;
    private JFrame parentPanel;
    private Connection conn;

    public ScheduledAlert(Alert alert, JFrame parentPanel, I_Bapers controller, DB_Connection conn) {
        this.conn = conn.getConn();
        this.alert = alert;
        this.controller = controller;
        this.parentPanel = parentPanel;
    }

    /**
     * Checks if an alert can actually run.
     * This is dictated by checking if the execution time is in the past, and is already not running.
     * @return True/False depending on whether it can run.
     */
    public boolean canRunAlert() {
        return alert.getTimeUntilExecutionInSeconds() > 0 && !isRunning;
    }

    /**
     * Executes an alert, assigning it to a ThreadPool that is held in Controller.
     * Utilises a ScheduledFuture, and tracks the execution time in seconds.
     * This is often ran on multiple ScheduledAlert objects.
     * It checks to see if the alert can actually run using @canRunAlert(), and if not, runs @removeFromLoadedAlerts().
     */
    public void runAlert() {
        if (canRunAlert()) {
            isRunning = true;
            alertHandle = controller.getScheduler().schedule(alertRunner, alert.getTimeUntilExecutionInSeconds(), TimeUnit.SECONDS);
            controller.getLoadedAlerts().add(this);
        }else if (alert.getTimeUntilExecutionInSeconds() < 0) {
            // Alert is already completed, get rid of it
            removeFromLoadedAlerts();
        }
    }

    /**
     * A Database query to fetch the payment deadline for the respective job ID.
     * @param jobId The specific JobId retrieve a payment deadline from.
     * @return A Timestamp, which is the respective deadline of a job (and therefore expected time the alert should be executed).
     */
    private Timestamp getPaymentDeadlineFromJobId(int jobId) {
        Timestamp paymentDeadline = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT Payment_deadline FROM Job WHERE Job_ID = ?");
            st.setInt(1, jobId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                paymentDeadline = rs.getTimestamp("Payment_deadline");
                rs.close();
                st.close();
            }
        } catch (Exception e) { e.printStackTrace(); }
        return paymentDeadline;
    }

    /**
     * A function which retrieves the job based on an ID.
     * @param jobId The Job ID to be searched for.
     * @return The Job object which is populated with data from the database.
     */
    private Job getJob(int jobId) {
        Job job = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?");
            st.setInt(1, jobId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                job = new Job(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(6),
                        rs.getString(4),
                        rs.getString(13),
                        rs.getTimestamp(12).toLocalDateTime(),
                        rs.getDouble(10)
                );
            }
            return job;
        } catch (Exception e) { e.printStackTrace(); }
        return job;
    }

    /**
     * Removes an alert from the systems currently loaded alerts.
     */
    public void removeFromLoadedAlerts() {
        controller.removeAlert(getAlert());
        controller.getLoadedAlerts().remove(this);
    }

    /**
     * A runnable class which is what is executed when a certain timeframe is reached.
     * Produces a dialog box on success.
     * Checks which are made are:
     *  If the current user is a shift or office manager.
     *  For payments:
     *      If a Payment alert is reached, however the payment is still pending, schedule another alert for
     *      15 minutes.
     */
    Runnable alertRunner = () -> {
        if (getJob(alert.getJobId()) == null) return;  // Check if the associated job for Alert is not empty.

        Job job = getJob(alert.getJobId());

        if (alert.getName().equals("job")) {
            if (controller.getCurrentUser().getRole().equals("Shift Manager")
                    || controller.getCurrentUser().getRole().equals("Office Manager")) {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "Job " + job.getJob_ID() + " deadline has been reached!",
                        "BAPERS", JOptionPane.WARNING_MESSAGE
                );
            }
        }else if (alert.getName().equals("payment")) {
            if (controller.getCurrentUser().getRole().equals("Shift Manager")
                    || controller.getCurrentUser().getRole().equals("Office Manager")) {
                if (!job.getJob_status().equals("Completed")) {
                    JOptionPane.showMessageDialog(
                            parentPanel,
                            "Job " + job.getJob_ID()  + " deadline requires payment!",
                            "BAPERS", JOptionPane.WARNING_MESSAGE
                    );
                    // If the payment is not completed, schedule another alret for 15 minutes in advance.
                    LocalDateTime newTime = getPaymentDeadlineFromJobId(alert.getJobId()).toLocalDateTime().plusMinutes(15);
                    controller.getAlertTransaction().create("payment", "", newTime, alert.getJobId());
                }else{
                    // If the payment is completed, no longer schedule anything else.
                    JOptionPane.showMessageDialog(
                            parentPanel,
                            "Job " + job.getJob_ID()  + " has received payment!",
                            "BAPERS", JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        }
        // Cleanup code after execution, remove the Alert from database and from loaded alerts, and set the running
        // state to false.
        removeFromLoadedAlerts();
        isRunning = false;
    };

    /**
     * Cancel the schedule for an Alert.
     * Removes it from the actual scheduler, so thread is removed from the pool.
     */
    public void cancelSchedule() {
        if (isRunning) {
            alertHandle.cancel(false);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Alert getAlert() {
        return alert;
    }

}
