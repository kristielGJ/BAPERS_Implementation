package model.database;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.regions.servicemetadata.BackupServiceMetadata;
import software.amazon.awssdk.services.backup.BackupClient;
import software.amazon.awssdk.services.backup.model.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static model.database.Secrets.awsCreds;

/**
 * Mushfikur Rahman
 * */
public class BackupManager {
    private AwsBasicCredentials creds = awsCreds;
    private BackupClient backupClient = BackupClient.builder()
                                                    .region(Region.of(Secrets.region))
                                                    .credentialsProvider(StaticCredentialsProvider.create(creds))
                                                    .build();

    public String restoreBackup(String recoveryPointArn) {
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
        StartRestoreJobResponse response = backupClient.startRestoreJob(restoreRequest);
        System.out.println(response.restoreJobId());
        return response.restoreJobId();
    }

    public String startBackup() {
        StartBackupJobRequest backupRequest = StartBackupJobRequest.builder()
                                                                   .backupVaultName(Secrets.vaultName)
                                                                   .iamRoleArn(Secrets.iamRoleArn)
                                                                   .resourceArn(Secrets.resourceArn)
                                                                   .build();
        StartBackupJobResponse response = backupClient.startBackupJob(backupRequest);
        return response.backupJobId();
    }

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
        UpdateBackupPlanRequest request = UpdateBackupPlanRequest.builder()
                                                                 .backupPlanId("4361dca1-b2ea-4bb0-9521-c85d41d134e6")
                                                                 .backupPlan(plan)
                                                                 .build();
        UpdateBackupPlanResponse backupPlanResponse = backupClient.updateBackupPlan(request);
        return backupPlanResponse.backupPlanId();
    }

    public List<BackupPlansListMember> getAllBackupPlans() {
        ListBackupPlansRequest backupPlanList = ListBackupPlansRequest.builder()
                                                                      .build();
        ListBackupPlansResponse backupPlans = backupClient.listBackupPlans(backupPlanList);
        return backupPlans.backupPlansList();
    }

    public BackupJob getJobFromId(String id) {
        for (BackupJob job : getAllBackups()) {
            if (job.backupJobId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    public boolean abortBackup(String backupJobId) {
        if (getJobFromId(backupJobId) != null) {
            if (getJobFromId(backupJobId).state().equals(BackupJobState.RUNNING)) {
                StopBackupJobRequest stopBackupJobRequest = StopBackupJobRequest.builder()
                        .backupJobId(backupJobId)
                        .build();
                StopBackupJobResponse response = backupClient.stopBackupJob(stopBackupJobRequest);
                return true;
            }
        }
        return false;
    }

    public List<BackupJob> getAllBackups() {
        ListBackupJobsRequest listRequest = ListBackupJobsRequest.builder()
                                                                 .byResourceArn(Secrets.resourceArn)
                                                                 .build();
        ListBackupJobsResponse response = backupClient.listBackupJobs(listRequest);
        return response.backupJobs();
    }
}
