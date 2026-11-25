package model;

import java.io.Serializable;

/**
 * PlayerNode - Represents an item in the LinkedList
 * This class holds player information and implements Comparable for sorting by jersey number
 */
public class PlayerNode implements Comparable<PlayerNode>, Serializable {
    private static final long serialVersionUID = 1L;

    private String playerId;
    private String name;
    private int age;
    private String position;
    private String team;
    private int jerseyNumber;
    private String preferredFoot;

    public PlayerNode(String playerId, String name, int age, String position,
                      String team, int jerseyNumber, String preferredFoot) {
        this.playerId = playerId;
        this.name = name;
        this.age = age;
        this.position = position;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
        this.preferredFoot = preferredFoot;
    }

    // Getters
    public String getPlayerId() { return playerId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPosition() { return position; }
    public String getTeam() { return team; }
    public int getJerseyNumber() { return jerseyNumber; }
    public String getPreferredFoot() { return preferredFoot; }

    // Setters
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPosition(String position) { this.position = position; }
    public void setTeam(String team) { this.team = team; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }
    public void setPreferredFoot(String preferredFoot) { this.preferredFoot = preferredFoot; }

    /**
     * Compare by jersey number for sorting (ascending order)
     * This is what allows the LinkedList to maintain sorted order
     */
    @Override
    public int compareTo(PlayerNode other) {
        return Integer.compare(this.jerseyNumber, other.jerseyNumber);
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s | %s | Age: %d | ID: %s | Foot: %s",
                jerseyNumber, name, position, team, age, playerId, preferredFoot);
    }

    public String toFormattedString() {
        return String.format("  %-3d | %-20s | %-12s | %-20s | %-3d | %-8s | %-5s",
                jerseyNumber,
                name.length() > 20 ? name.substring(0, 17) + "..." : name,
                position,
                team.length() > 20 ? team.substring(0, 17) + "..." : team,
                age,
                playerId,
                preferredFoot);
    }
}