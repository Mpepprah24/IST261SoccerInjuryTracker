package model;

import java.io.*;
import java.util.ArrayList;

public class TeamList implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Team> teams;
    private int currentIndex;

    public TeamList() {
        teams = new ArrayList<>();
        currentIndex = 0;
    }

    public static TeamList createWithSampleData() {
        TeamList list = new TeamList();
        list.populateSampleData();
        return list;
    }

    private void populateSampleData() {
        teams.add(new Team("SSC Napoli", "Antonio Conte",
                "Naples, Italy", "Serie A",
                "Scott McTominay, Giovanni Di Lorenzo, Alex Meret"));
        teams.add(new Team("Inter Milan", "Cristian Chivu",
                "Milan, Italy", "Serie A",
                "Lautaro Martinez, Nicolo Barella, Marcus Thuram"));
        teams.add(new Team("Manchester City", "Pep Guardiola",
                "Manchester, England", "Premier League",
                "Erling Haaland, Phil Foden, Ruben Dias"));
        teams.add(new Team("Real Madrid", "Alvaro Arbeloa",
                "Madrid, Spain", "La Liga",
                "Kylian Mbappe, Federico Valverde, Vinicius Jr."));
        teams.add(new Team("FC Barcelona", "Hansi Flick",
                "Barcelona, Spain", "La Liga",
                "Lamine Yamal, Robert Lewandowski, Pedri"));
        teams.add(new Team("Paris Saint-Germain", "Luis Enrique",
                "Paris, France", "Ligue 1",
                "Ousmane Dembele, Khvicha Kvaratskhelia, Bradley Barcola"));
        teams.add(new Team("Bayern Munich", "Vincent Kompany",
                "Munich, Germany", "Bundesliga",
                "Harry Kane, Michael Olise, Luis Diaz"));
        teams.add(new Team("Arsenal", "Mikel Arteta",
                "London, England", "Premier League",
                "Bukayo Saka, Martin Odegaard, Viktor Gyokeres"));
        teams.add(new Team("Liverpool", "Arne Slot",
                "Liverpool, England", "Premier League",
                "Mohamed Salah, Virgil van Dijk, Florian Wirtz"));
        teams.add(new Team("Juventus", "Luciano Spalletti",
                "Turin, Italy", "Serie A",
                "Dusan Vlahovic, Kenan Yildiz, Weston McKennie"));
    }

    public Team getCurrentTeam() {
        if (teams.isEmpty()) return null;
        return teams.get(currentIndex);
    }

    public Team getFirstTeam() {
        if (teams.isEmpty()) return null;
        currentIndex = 0;
        return teams.get(currentIndex);
    }

    public Team getLastTeam() {
        if (teams.isEmpty()) return null;
        currentIndex = teams.size() - 1;
        return teams.get(currentIndex);
    }

    public Team getNextTeam() {
        if (teams.isEmpty()) return null;
        if (currentIndex < teams.size() - 1) {
            currentIndex++;
        }
        return teams.get(currentIndex);
    }

    public Team getPreviousTeam() {
        if (teams.isEmpty()) return null;
        if (currentIndex > 0) {
            currentIndex--;
        }
        return teams.get(currentIndex);
    }

    public boolean hasNext() {
        return !teams.isEmpty() && currentIndex < teams.size() - 1;
    }

    public boolean hasPrevious() {
        return !teams.isEmpty() && currentIndex > 0;
    }

    public void addTeam(Team team) {
        teams.add(team);
        currentIndex = teams.size() - 1;
    }

    public void updateCurrentTeam(Team updatedTeam) {
        if (!teams.isEmpty() && currentIndex >= 0 && currentIndex < teams.size()) {
            teams.set(currentIndex, updatedTeam);
        }
    }

    public void deleteCurrentTeam() {
        if (!teams.isEmpty() && currentIndex >= 0 && currentIndex < teams.size()) {
            teams.remove(currentIndex);
            if (currentIndex >= teams.size() && currentIndex > 0) {
                currentIndex--;
            }
        }
    }

    public Team getTeamByName(String teamName) {
        for (Team t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                return t;
            }
        }
        return null;
    }

    public boolean teamNameExists(String teamName) {
        for (Team t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeamByName(String teamName) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().equalsIgnoreCase(teamName)) {
                teams.remove(i);
                if (currentIndex >= teams.size() && currentIndex > 0) {
                    currentIndex--;
                }
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return teams.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public ArrayList<Team> getAllTeams() {
        return teams;
    }

    public boolean isEmpty() {
        return teams.isEmpty();
    }

    public boolean saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("Teams saved to: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving teams: " + e.getMessage());
            return false;
        }
    }

    public static TeamList loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No team save file found, creating TeamList with sample data");
            return TeamList.createWithSampleData();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            TeamList loaded = (TeamList) ois.readObject();
            System.out.println("Teams loaded from: " + filePath);
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading teams: " + e.getMessage());
            System.out.println("Creating new TeamList with sample data");
            return TeamList.createWithSampleData();
        }
    }

    @Override
    public String toString() {
        return "TeamList with " + teams.size() + " teams";
    }
}