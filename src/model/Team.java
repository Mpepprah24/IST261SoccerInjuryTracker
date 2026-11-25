package model;

import java.io.Serializable;

public class Team implements Serializable {
    private static final long serialVersionUID = 1L;

    private String teamName;
    private String coach;
    private String location;
    private String league;
    private String roster;

    public Team(String teamName, String coach, String location, String league, String roster) {
        this.teamName = teamName;
        this.coach = coach;
        this.location = location;
        this.league = league;
        this.roster = roster;
    }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }
    public String getRoster() { return roster; }
    public void setRoster(String roster) { this.roster = roster; }

    @Override
    public String toString() {
        return "Team Details:\n" +
                "  Team Name: " + teamName + "\n" +
                "  Coach: " + coach + "\n" +
                "  Location: " + location + "\n" +
                "  League: " + league + "\n" +
                "  Roster Info: " + roster;
    }
}