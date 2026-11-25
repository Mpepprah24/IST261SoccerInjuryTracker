package controller;

import model.Player;
import model.PlayerList;
import model.Team;
import model.TeamList;
import model.Injury;
import model.InjuryManager;
import view.MainPlayerView;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class PlayerController {
    private PlayerList playerList;
    private TeamList teamList;
    private InjuryManager injuryManager;
    private MainPlayerView mainView;

    private static final String PLAYER_DATA_FILE = "data/players.dat";
    private static final String TEAM_DATA_FILE = "data/teams.dat";

    public PlayerController() {
        createDataDirectory();

        playerList = PlayerList.loadFromFile(PLAYER_DATA_FILE);
        teamList = TeamList.loadFromFile(TEAM_DATA_FILE);
        injuryManager = new InjuryManager();

        // Update player clearance statuses based on injuries
        updateAllPlayerStatuses();

        mainView = new MainPlayerView(this);
        mainView.setVisible(true);

        setupShutdownHook();
    }

    private void updateAllPlayerStatuses() {
        for (Player player : playerList.getAllPlayers()) {
            String status = injuryManager.getPlayerClearanceStatus(player.getPlayerID());
            player.setClearanceStatus(status);
        }
    }

    // Update a single player's status based on injuries
    public void updatePlayerStatus(String playerId) {
        Player player = playerList.getPlayerById(playerId);
        if (player != null) {
            String status = injuryManager.getPlayerClearanceStatus(playerId);
            player.setClearanceStatus(status);
        }
    }

    // Update player clearance status manually
    public void updateClearanceStatus(String playerId, String newStatus) {
        Player player = playerList.getPlayerById(playerId);
        if (player != null) {
            player.setClearanceStatus(newStatus);
            if (mainView != null) {
                mainView.refreshPlayerTable();
            }
        }
    }

    private void createDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
            System.out.println("Created /data directory");
        }
    }

    private void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Saving data before exit...");
            saveData();
        }));
    }

    public void saveData() {
        playerList.saveToFile(PLAYER_DATA_FILE);
        teamList.saveToFile(TEAM_DATA_FILE);
        System.out.println("Data saved successfully");
    }

    // ==================== PLAYER CRUD METHODS ====================

    public List<Player> getAllPlayers() {
        return playerList.getAllPlayers();
    }

    public Player getPlayerById(String playerId) {
        return playerList.getPlayerById(playerId);
    }

    // ADD PLAYER
    public boolean addPlayer(String name, int age, String playerId, String position,
                             String team, int jerseyNumber, String preferredFoot) {
        // Check if player ID already exists
        if (playerList.playerIdExists(playerId)) {
            return false;
        }

        Player newPlayer = new Player(name, age, playerId, position, team, jerseyNumber, preferredFoot);
        playerList.addPlayer(newPlayer);

        if (mainView != null) {
            mainView.refreshPlayerTable();
        }
        return true;
    }

    // UPDATE PLAYER
    public boolean updatePlayer(String oldId, String name, int age, String newId,
                                String position, String team, int jerseyNumber, String preferredFoot) {
        Player existingPlayer = playerList.getPlayerById(oldId);
        if (existingPlayer == null) {
            return false;
        }

        // If ID changed, check if new ID already exists
        if (!oldId.equals(newId) && playerList.playerIdExists(newId)) {
            return false;
        }

        Player updatedPlayer = new Player(name, age, newId, position, team, jerseyNumber, preferredFoot);

        if (!oldId.equals(newId)) {
            playerList.deletePlayerById(oldId);
            playerList.addPlayer(updatedPlayer);
        } else {
            playerList.updatePlayer(oldId, updatedPlayer);
        }

        if (mainView != null) {
            mainView.refreshPlayerTable();
        }
        return true;
    }

    // DELETE PLAYER
    public boolean deletePlayer(String playerId) {
        Player player = playerList.getPlayerById(playerId);
        if (player == null) {
            return false;
        }

        playerList.deletePlayerById(playerId);

        if (mainView != null) {
            mainView.refreshPlayerTable();
        }
        return true;
    }

    // ==================== TEAM METHODS ====================

    public List<Team> getAllTeams() {
        return teamList.getAllTeams();
    }

    public void addTeam(String teamName, String coach, String location, String league, String roster) {
        Team newTeam = new Team(teamName, coach, location, league, roster);
        teamList.addTeam(newTeam);
    }

    public void updateTeam(String oldName, String teamName, String coach, String location, String league, String roster) {
        Team updatedTeam = new Team(teamName, coach, location, league, roster);
        teamList.deleteTeamByName(oldName);
        teamList.addTeam(updatedTeam);
    }

    public void deleteTeam(String teamName) {
        teamList.deleteTeamByName(teamName);
    }

    // ==================== INJURY METHODS ====================

    public List<Injury> getPlayerInjuries(String playerId) {
        return injuryManager.getPlayerInjuries(playerId);
    }

    public String addInjury(String playerId, String injuryType, String injuryDate,
                            String severity, String bodyPart, boolean requiresSurgery,
                            int weeksRecovery, String recoveryStatus) {
        if (playerId == null || playerId.trim().isEmpty()) {
            return "Player ID cannot be empty";
        }

        Injury newInjury = new Injury(injuryType, injuryDate, severity, bodyPart,
                requiresSurgery, weeksRecovery, playerId);
        newInjury.setRecoveryStatus(recoveryStatus);
        injuryManager.addInjury(newInjury);

        // Update player's clearance status
        updatePlayerStatus(playerId);
        if (mainView != null) {
            mainView.refreshPlayerTable();
        }

        return "Injury added successfully for Player ID: " + playerId;
    }

    public String updateInjuryRecoveryStatus(String playerId, int injuryIndex, String newStatus) {
        List<Injury> injuries = injuryManager.getPlayerInjuries(playerId);
        if (injuryIndex >= 0 && injuryIndex < injuries.size()) {
            injuries.get(injuryIndex).setRecoveryStatus(newStatus);
            updatePlayerStatus(playerId);
            if (mainView != null) {
                mainView.refreshPlayerTable();
            }
            return "Recovery status updated to: " + newStatus;
        }
        return "Injury not found";
    }

    public String deleteInjury(String playerId, int injuryIndex) {
        boolean removed = injuryManager.removeInjury(playerId, injuryIndex);
        if (removed) {
            updatePlayerStatus(playerId);
            if (mainView != null) {
                mainView.refreshPlayerTable();
            }
            return "Injury deleted successfully";
        }
        return "Injury not found";
    }

    // ==================== EXIT APPLICATION ====================

    public void exitApplication() {
        saveData();
        System.exit(0);
    }

    public String getPlayerStatusMessage() {
        if (playerList.isEmpty()) {
            return "No players in list";
        }
        return "Players: " + playerList.getSize() + " total";
    }

    public String getTeamStatusMessage() {
        if (teamList.isEmpty()) {
            return "No teams in list";
        }
        return "Teams: " + teamList.getSize() + " total";
    }
}