package model;

public interface Recoverable {
    String estimateRecoveryTime();
    String getRecoveryStatus();
    String getTreatmentPlan();
}