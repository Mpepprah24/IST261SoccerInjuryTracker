package model;

public class RecoveryTimeline implements Recoverable {
    private String startDate, estimatedEndDate, currentPhase, clearanceStatus;
    private String playerName;
    private int percentageComplete;

    public RecoveryTimeline(String startDate, String estimatedEndDate,
                            String currentPhase, String clearanceStatus,
                            String playerName, int percentageComplete) {
        this.startDate = startDate;
        this.estimatedEndDate = estimatedEndDate;
        this.currentPhase = currentPhase;
        this.clearanceStatus = clearanceStatus;
        this.playerName = playerName;
        this.percentageComplete = percentageComplete;
    }

    public RecoveryTimeline(String startDate, String estimatedEndDate,
                            String currentPhase, String clearanceStatus) {
        this(startDate, estimatedEndDate, currentPhase, clearanceStatus, "Unknown", 0);
    }

    @Override
    public String estimateRecoveryTime() {
        if (estimatedEndDate == null || startDate == null) {
            return "Recovery timeline not set";
        }
        return "From " + startDate + " to " + estimatedEndDate;
    }

    @Override
    public String getRecoveryStatus() {
        return playerName + " is " + percentageComplete + "% through recovery. " +
                "Current phase: " + currentPhase + ". " +
                "Clearance status: " + clearanceStatus;
    }

    @Override
    public String getTreatmentPlan() {
        return "Recovery plan for " + playerName + ":\n" +
                "  Phase 1: Initial rest and protection\n" +
                "  Phase 2: Physical therapy\n" +
                "  Phase 3: Strength training\n" +
                "  Phase 4: Sport-specific drills\n" +
                "  Phase 5: Full return to play";
    }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEstimatedEndDate() { return estimatedEndDate; }
    public void setEstimatedEndDate(String estimatedEndDate) { this.estimatedEndDate = estimatedEndDate; }
    public String getCurrentPhase() { return currentPhase; }
    public void setCurrentPhase(String currentPhase) { this.currentPhase = currentPhase; }
    public String getClearanceStatus() { return clearanceStatus; }
    public void setClearanceStatus(String clearanceStatus) { this.clearanceStatus = clearanceStatus; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getPercentageComplete() { return percentageComplete; }
    public void setPercentageComplete(int percentageComplete) { this.percentageComplete = percentageComplete; }

    @Override
    public String toString() {
        return "Recovery Timeline Details:\n" +
                "  Player: " + playerName + "\n" +
                "  Start Date: " + startDate + "\n" +
                "  Estimated End Date: " + estimatedEndDate + "\n" +
                "  Current Phase: " + currentPhase + "\n" +
                "  Clearance Status: " + clearanceStatus + "\n" +
                "  Progress: " + percentageComplete + "% complete";
    }
}