package model.database;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.backup.BackupClient;
import software.amazon.awssdk.services.backup.model.*;
import java.util.List;

import static model.database.Secrets.awsCreds;

/**
 * A class to handle the connection to the AWS API to allow for backups and the restoration of backups.
 */
public class BackupManager {
    private AwsBasicCredentials creds = awsCreds;
    private BackupClient backupClient = BackupClient.builder()
                                                    .region(Region.of(Secrets.region))
                                                    .credentialsProvider(StaticCredentialsProvider.create(creds))
                                                    .build();

    /**
     * Starts the execution of the restore backup phase.
     * This requires metadata of the specific backup instance.
     * @param recoveryPointArn The ARN value for the specific point we want to recover (returned when a backup is created).
     * @return A string of the id of the backup.
     */
    public String restoreBackup(String recoveryPointArn) {
        // Getting the metadata to use in the restore job request.
        GetRecoveryPointRestoreMetadataRequest metadataRequest = GetRecoveryPointRestoreMetadataRequest.builder()
                                                                                                        .backupVaultName(Secrets.vaultName)
                                                                                                        .recoveryPointArn(recoveryPointArn)
                                                                                                        .build();
        GetRecoveryPointRestoreMetadataResponse metadataResponse = backupClient.getRecoveryPointRestoreMetadata(metadataRequest);
        StartRestoreJobRequest restoreRequest = StartRestoreJobRequest.builder()
                                                                      .recoveryPointArn(recoveryPointArn)
                                                                      .iamRoleArn(Secrets.iamRoleArn)
                                                                      .metadata(metadataResponse.restoreMetadata())
                                                                      .build();
        // Actually execute the restore job!
        StartRestoreJobResponse response = backupClient.startRestoreJob(restoreRequest);
        System.out.println(response.restoreJobId());
        return response.restoreJobId();
    }

    /**
     * Executes a backup on AWS Backup.
     * @return
     */
    public String startBackup() {
        StartBackupJobRequest backupRequest = StartBackupJobRequest.builder()
                                                                   .backupVaultName(Secrets.vaultName)
                                                                   .iamRoleArn(Secrets.iamRoleArn)
                                                                   .resourceArn(Secrets.resourceArn)
                                                                   .build();
        StartBackupJobResponse response = backupClient.startBackupJob(backupRequest);
        return response.backupJobId();
    }

    /**
     * Changes the frequency of the interval in which the database is backup (edits a main Backup Plan)
     * The frequency is actually specified by a cron expression.
     * @param frequency A string which is either "Daily", "Weekly" or "Monthly". Specifies how often.
     * @return The id of the backup plan edited.
     */
    public String changeFrequency(String frequency) {
        BackupPlanInput plan;
        BackupRuleInput rule = null;
        if (frequency.equals("Daily")) {
            rule = BackupRuleInput.builder()
                                  .ruleName("MainBackupRule")
                                  .targetBackupVaultName("Default")
                                  .scheduleExpression("cron(0 5 ? * * *)")
                                  .build();
        }else if(frequency.equals("Weekly")) {
            rule = BackupRuleInput.builder()
                                  .ruleName("MainBackupRule")
                                  .targetBackupVaultName("Default")
                                  .scheduleExpression("cron(0 5 ? * 7 *)")
                                  .build();
        }else if(frequency.equals("Monthly")) {
            rule = BackupRuleInput.builder()
                                  .ruleName("MainBackupRule")
                                  .targetBackupVaultName("Default")
                                  .scheduleExpression("cron(0 5 1 * ? *)")
                                  .build();
        }
        plan = BackupPlanInput.builder()
                .backupPlanName("BapersBackupPlan")
                .rules(rule)
                .build();
        // Actually execute the editing of a backup plan.
        UpdateBackupPlanRequest request = UpdateBackupPlanRequest.builder()
                                                                 // Editing "MainBackupPlan"
                                                                 .backupPlanId("4361dca1-b2ea-4bb0-9521-c85d41d134e6")
                                                                 .backupPlan(plan)
                                                                 .build();
        UpdateBackupPlanResponse backupPlanResponse = backupClient.updateBackupPlan(request);
        return backupPlanResponse.backupPlanId();
    }

    /**
     * A function which gets a BackupJob from a certain id.
     * @param id The requested BackupJob id.
     * @return The respective BackupJob object, or null if not found.
     */
    public BackupJob getJobFromId(String id) {
        for (BackupJob job : getAllBackups()) {
            if (job.backupJobId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    /**
     * A function which aborts a backup that may be currently running.
     * This function:
     *  Checks the state of a BackupJob, and if it is running then:
     *     Allow the request to be sent to abort the BackupJob from executing.
     * @param backupJobId The specific backupJobId to abort.
     * @return A boolean value depending on whether the abortion of a backup was successful.
     */
    public boolean abortBackup(String backupJobId) {
        if (getJobFromId(backupJobId) != null) {
            if (getJobFromId(backupJobId).state().equals(BackupJobState.RUNNING)) {
                StopBackupJobRequest stopBackupJobRequest = StopBackupJobRequest.builder()
                                                                                .backupJobId(backupJobId)
                                                                                .build();
                backupClient.stopBackupJob(stopBackupJobRequest);
                return true;
            }
        }
        return false;
    }

    /**
     * Method that retrievs all BackupJobs.
     * @return A list of BackupJobs.
     */
    public List<BackupJob> getAllBackups() {
        ListBackupJobsRequest listRequest = ListBackupJobsRequest.builder()
                                                                 .byResourceArn(Secrets.resourceArn)
                                                                 .build();
        ListBackupJobsResponse response = backupClient.listBackupJobs(listRequest);
        return response.backupJobs();
    }
}
