package model;

import java.io.Serializable;

public class Player extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String team;
    private String position;
    private String playerID;
    private int jerseyNumber;
    private String preferredFoot;
    private String clearanceStatus;  // NEW: "RED", "YELLOW", "GREEN"

    public Player(String name, int age, String playerID, String position,
                  String team, int jerseyNumber, String preferredFoot) {
        super(name, age, playerID);
        this.position = position;
        this.team = team;
        this.playerID = playerID;
        this.jerseyNumber = jerseyNumber;
        this.preferredFoot = preferredFoot;
        this.clearanceStatus = "GREEN";  // Default: cleared to play
    }

    public Player(String name, String team, String position, String playerID, int age) {
        super(name, age, playerID);
        this.position = position;
        this.team = team;
        this.playerID = playerID;
        this.jerseyNumber = 0;
        this.preferredFoot = "Unknown";
        this.clearanceStatus = "GREEN";
    }

    @Override
    public String getRole() {
        return "Player - Position: " + position;
    }

    @Override
    public String getDescription() {
        return "PLAYER: " + name + " (Age " + age + ") wears #" + jerseyNumber +
                ", plays " + position + " for " + team +
                ". Preferred foot: " + preferredFoot +
                ". Status: " + clearanceStatus;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getPlayerID() { return playerID; }
    public void setPlayerID(String playerID) { this.playerID = playerID; }
    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }
    public String getPreferredFoot() { return preferredFoot; }
    public void setPreferredFoot(String preferredFoot) { this.preferredFoot = preferredFoot; }

    // NEW: Clearance status methods
    public String getClearanceStatus() { return clearanceStatus; }
    public void setClearanceStatus(String clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
    }

    public String getFullDetails() {
        return String.format("%s (Age: %d) - %s, #%d for %s (Foot: %s) [Status: %s]",
                name, age, position, jerseyNumber, team, preferredFoot, clearanceStatus);
    }

    @Override
    public String toString() {
        return "Player Details:\n" +
                "  Name: " + name + "\n" +
                "  Age: " + age + "\n" +
                "  Position: " + position + "\n" +
                "  Team: " + team + "\n" +
                "  Player ID: " + playerID + "\n" +
                "  Jersey #: " + jerseyNumber + "\n" +
                "  Preferred Foot: " + preferredFoot + "\n" +
                "  Clearance Status: " + clearanceStatus;
    }
}