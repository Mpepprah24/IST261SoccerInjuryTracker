package model;

import java.io.*;
import java.util.ArrayList;

public class PlayerList implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Player> players;
    private int currentIndex;

    public PlayerList() {
        players = new ArrayList<>();
        currentIndex = 0;
    }

    private void populateSampleData() {
        players.add(new Player("Lionel Messi", 38, "MESSI010", "Right Winger",
                "Inter Miami", 10, "Left"));
        players.add(new Player("Lamine Yamal", 18, "YAMAL027", "Right Winger",
                "FC Barcelona", 10, "Left"));
        players.add(new Player("Mohammed Kudus", 25, "KUDUS020", "Midfielder",
                "Tottenham Hotspur", 20, "Right"));
        players.add(new Player("Florian Wirtz", 22, "WIRTZ007", "Midfielder",
                "Liverpool", 7, "Right"));
        players.add(new Player("Desire Doue", 20, "DOUE009", "Right Winger",
                "PSG", 9, "Right"));
        players.add(new Player("Scott McTominay", 29, "MCTOMINAY039", "Midfielder",
                "Napoli", 39, "Right"));
        players.add(new Player("Rafael Leao", 26, "LEAO010", "Left Winger",
                "AC Milan", 10, "Right"));
        players.add(new Player("Alessandro Bastoni", 26, "BASTONI095", "Center Back",
                "Inter Milan", 95, "Left"));
    }

    public static PlayerList createWithSampleData() {
        PlayerList list = new PlayerList();
        list.populateSampleData();
        return list;
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) return null;
        return players.get(currentIndex);
    }

    public Player getFirstPlayer() {
        if (players.isEmpty()) return null;
        currentIndex = 0;
        return players.get(currentIndex);
    }

    public Player getLastPlayer() {
        if (players.isEmpty()) return null;
        currentIndex = players.size() - 1;
        return players.get(currentIndex);
    }

    public Player getNextPlayer() {
        if (players.isEmpty()) return null;
        if (currentIndex < players.size() - 1) {
            currentIndex++;
        }
        return players.get(currentIndex);
    }

    public Player getPreviousPlayer() {
        if (players.isEmpty()) return null;
        if (currentIndex > 0) {
            currentIndex--;
        }
        return players.get(currentIndex);
    }

    public boolean hasNext() {
        return !players.isEmpty() && currentIndex < players.size() - 1;
    }

    public boolean hasPrevious() {
        return !players.isEmpty() && currentIndex > 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
        currentIndex = players.size() - 1;
    }

    public void updateCurrentPlayer(Player updatedPlayer) {
        if (!players.isEmpty() && currentIndex >= 0 && currentIndex < players.size()) {
            players.set(currentIndex, updatedPlayer);
        }
    }

    public void deleteCurrentPlayer() {
        if (!players.isEmpty() && currentIndex >= 0 && currentIndex < players.size()) {
            players.remove(currentIndex);
            if (currentIndex >= players.size() && currentIndex > 0) {
                currentIndex--;
            }
        }
    }

    public Player getPlayerById(String playerId) {
        for (Player p : players) {
            if (p.getPlayerID().equals(playerId)) {
                return p;
            }
        }
        return null;
    }

    public boolean deletePlayerById(String playerId) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerID().equals(playerId)) {
                players.remove(i);
                if (currentIndex >= players.size() && currentIndex > 0) {
                    currentIndex--;
                }
                return true;
            }
        }
        return false;
    }

    public boolean playerIdExists(String playerId) {
        for (Player p : players) {
            if (p.getPlayerID().equals(playerId)) {
                return true;
            }
        }
        return false;
    }

    public boolean updatePlayer(String oldId, Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerID().equals(oldId)) {
                players.set(i, updatedPlayer);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return players.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public ArrayList<Player> getAllPlayers() {
        return players;
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public boolean saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("Players saved to: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving players: " + e.getMessage());
            return false;
        }
    }

    public static PlayerList loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No save file found, creating new PlayerList with sample data");
            return PlayerList.createWithSampleData();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            PlayerList loaded = (PlayerList) ois.readObject();
            System.out.println("Players loaded from: " + filePath);
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading players: " + e.getMessage());
            System.out.println("Creating new PlayerList with sample data");
            return PlayerList.createWithSampleData();
        }
    }

    @Override
    public String toString() {
        return "PlayerList with " + players.size() + " players";
    }
}