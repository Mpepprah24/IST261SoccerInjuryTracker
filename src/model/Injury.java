package model;

import java.io.Serializable;

public class Injury implements Recoverable, Serializable {
    private static final long serialVersionUID = 1L;

    private String injuryType;
    private String injuryDate;
    private String severity;
    private String bodyPart;
    private boolean requiresSurgery;
    private int estimatedWeeksRecovery;
    private String playerId;
    private String recoveryStatus;  // NEW: "Recovering", "Almost Recovered", "Recovered"

    public Injury(String injuryType, String injuryDate, String severity, String bodyPart,
                  boolean requiresSurgery, int estimatedWeeksRecovery, String playerId) {
        this.injuryType = injuryType;
        this.injuryDate = injuryDate;
        this.severity = severity;
        this.bodyPart = bodyPart;
        this.requiresSurgery = requiresSurgery;
        this.estimatedWeeksRecovery = estimatedWeeksRecovery;
        this.playerId = playerId;
        this.recoveryStatus = "Recovering";
    }

    public Injury(String injuryType, String injuryDate, String severity, String bodyPart) {
        this(injuryType, injuryDate, severity, bodyPart, false, 0, "");
        this.recoveryStatus = "Recovering";
    }

    // Getters
    public String getInjuryType() { return injuryType; }
    public String getInjuryDate() { return injuryDate; }
    public String getSeverity() { return severity; }
    public String getBodyPart() { return bodyPart; }
    public boolean isRequiresSurgery() { return requiresSurgery; }
    public int getEstimatedWeeksRecovery() { return estimatedWeeksRecovery; }
    public String getPlayerId() { return playerId; }
    public String getRecoveryStatus() { return recoveryStatus; }

    // Setters
    public void setInjuryType(String injuryType) { this.injuryType = injuryType; }
    public void setInjuryDate(String injuryDate) { this.injuryDate = injuryDate; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setBodyPart(String bodyPart) { this.bodyPart = bodyPart; }
    public void setRequiresSurgery(boolean requiresSurgery) { this.requiresSurgery = requiresSurgery; }
    public void setEstimatedWeeksRecovery(int estimatedWeeksRecovery) {
        this.estimatedWeeksRecovery = estimatedWeeksRecovery;
    }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public void setRecoveryStatus(String recoveryStatus) { this.recoveryStatus = recoveryStatus; }

    @Override
    public String estimateRecoveryTime() {
        if (estimatedWeeksRecovery > 0) {
            return estimatedWeeksRecovery + " weeks";
        }
        switch (injuryType) {
            case "ACL Tear":
                return severity.equals("High") ? "6-9 months" : "4-6 months";
            case "Hamstring Strain":
                return severity.equals("High") ? "8-12 weeks" : "3-6 weeks";
            default:
                return "Varies based on severity";
        }
    }

    @Override
    public String getTreatmentPlan() {
        StringBuilder plan = new StringBuilder("Treatment plan for " + injuryType + ": ");
        if (requiresSurgery) {
            plan.append("Surgery followed by ");
        }
        plan.append("physical therapy, rest, and gradual return to activity");
        return plan.toString();
    }

    public String toShortString() {
        return injuryType + " (" + injuryDate + ") - " + severity + " [" + recoveryStatus + "]";
    }

    @Override
    public String toString() {
        return "Injury Details:\n" +
                "  Player ID: " + playerId + "\n" +
                "  Type: " + injuryType + "\n" +
                "  Date: " + injuryDate + "\n" +
                "  Severity: " + severity + "\n" +
                "  Body Part: " + bodyPart + "\n" +
                "  Surgery Required: " + (requiresSurgery ? "Yes" : "No") + "\n" +
                "  Recovery Status: " + recoveryStatus;
    }
}