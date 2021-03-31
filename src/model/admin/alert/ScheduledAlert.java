package model.admin.alert;

import model.database.I_Bapers;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledAlert {

    private Alert alert;
    private I_Bapers controller;
    private boolean isRunning;
    ScheduledFuture<?> alertHandle;
    private JFrame parentPanel;

    public ScheduledAlert(Alert alert, JFrame parentPanel, I_Bapers controller) {
        this.alert = alert;
        this.controller = controller;
        this.parentPanel = parentPanel;
    }

    public boolean canRunAlert() {
        return alert.getTimeUntilExecutionInSeconds() > 0 && !isRunning;
    }

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

    private void removeFromLoadedAlerts() {
        controller.removeAlert(getAlert());
        controller.getLoadedAlerts().remove(this);
    }

    Runnable alertRunner = () -> {
        if (alert.getName() == "job") {
            if (controller.getCurrentUser().getRole() == "Shift Manager" || controller.getCurrentUser().getRole() == "Office Manager") {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "Job deadline has been reached!",
                        "BAPERS", JOptionPane.WARNING_MESSAGE
                );
            }
        }else if (alert.getName() == "payment") {
            if (controller.getCurrentUser().getRole() == "Shift Manager" || controller.getCurrentUser().getRole() == "Office Manager") {
                JOptionPane.showMessageDialog(
                        parentPanel,
                        "Payment deadline has been reached!",
                        "BAPERS", JOptionPane.WARNING_MESSAGE
                );
            }
        }
        removeFromLoadedAlerts();
        isRunning = false;
    };

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
