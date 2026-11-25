package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InjuryManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private HashMap<String, ArrayList<Injury>> playerInjuries;

    public InjuryManager() {
        playerInjuries = new HashMap<>();
        populateSampleData();
    }

    private void populateSampleData() {
        // Lionel Messi (MESSI010) - Hamstring injury (Almost Recovered - YELLOW)
        Injury messiInjury = new Injury("Hamstring Strain", "2024-02-10", "Medium", "Right Thigh",
                false, 6, "MESSI010");
        messiInjury.setRecoveryStatus("Almost Recovered");
        addInjury(messiInjury);

        // Lamine Yamal (YAMAL027) - Knee injury (Recovering - YELLOW)
        Injury yamalInjury = new Injury("Knee Contusion", "2024-03-01", "Medium", "Right Knee",
                false, 4, "YAMAL027");
        yamalInjury.setRecoveryStatus("Recovering");
        addInjury(yamalInjury);

        // Florian Wirtz (WIRTZ007) - ACL Tear (Long-term recovering - RED)
        Injury wirtzInjury = new Injury("ACL Tear", "2024-01-05", "High", "Left Knee",
                true, 36, "WIRTZ007");
        wirtzInjury.setRecoveryStatus("Recovering (Long-term)");
        addInjury(wirtzInjury);

        // Mohammed Kudus (KUDUS020) - Ankle sprain (Recovered - GREEN)
        Injury kudusInjury = new Injury("Ankle Sprain", "2024-02-20", "Low", "Right Ankle",
                false, 2, "KUDUS020");
        kudusInjury.setRecoveryStatus("Recovered");
        addInjury(kudusInjury);

        // Scott McTominay (MCTOMINAY039) - Groin strain (Recovering - YELLOW)
        Injury mctominayInjury = new Injury("Groin Strain", "2024-03-10", "Medium", "Groin",
                false, 5, "MCTOMINAY039");
        mctominayInjury.setRecoveryStatus("Recovering");
        addInjury(mctominayInjury);

        // Rafael Leao (LEAO010) - Shoulder injury (Almost Recovered - YELLOW)
        Injury leaoInjury = new Injury("Shoulder Dislocation", "2024-02-28", "High", "Right Shoulder",
                false, 8, "LEAO010");
        leaoInjury.setRecoveryStatus("Almost Recovered");
        addInjury(leaoInjury);

        // Alessandro Bastoni (BASTONI095) - Calf strain (Recovered - GREEN)
        Injury bastoniInjury = new Injury("Calf Strain", "2024-03-05", "Low", "Left Calf",
                false, 3, "BASTONI095");
        bastoniInjury.setRecoveryStatus("Recovered");
        addInjury(bastoniInjury);

        // Scott McTominay - additional injury
        Injury mctominayInjury2 = new Injury("Ankle Sprain", "2024-02-01", "Low", "Left Ankle",
                false, 3, "MCTOMINAY039");
        mctominayInjury2.setRecoveryStatus("Recovered");
        addInjury(mctominayInjury2);
    }

    public void addInjury(Injury injury) {
        String playerId = injury.getPlayerId();
        if (playerId == null || playerId.isEmpty()) {
            return;
        }

        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        if (injuries == null) {
            injuries = new ArrayList<>();
            playerInjuries.put(playerId, injuries);
        }
        injuries.add(injury);
    }

    public List<Injury> getPlayerInjuries(String playerId) {
        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        return injuries != null ? new ArrayList<>(injuries) : new ArrayList<>();
    }

    public boolean removeInjury(String playerId, int index) {
        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        if (injuries != null && index >= 0 && index < injuries.size()) {
            injuries.remove(index);
            if (injuries.isEmpty()) {
                playerInjuries.remove(playerId);
            }
            return true;
        }
        return false;
    }

    public List<Injury> searchInjuries(String playerId, String searchTerm) {
        List<Injury> results = new ArrayList<>();
        ArrayList<Injury> injuries = playerInjuries.get(playerId);

        if (injuries != null && searchTerm != null && !searchTerm.isEmpty()) {
            for (Injury injury : injuries) {
                if (injury.getInjuryType().toLowerCase().contains(searchTerm.toLowerCase())) {
                    results.add(injury);
                }
            }
        }
        return results;
    }

    public List<String> getAllPlayerIdsWithInjuries() {
        return new ArrayList<>(playerInjuries.keySet());
    }

    public int getTotalInjuryCount() {
        int count = 0;
        for (ArrayList<Injury> injuries : playerInjuries.values()) {
            count += injuries.size();
        }
        return count;
    }

    public boolean hasInjuries(String playerId) {
        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        return injuries != null && !injuries.isEmpty();
    }

    // NEW: Check if player has any active (non-recovered) injuries
    public boolean hasActiveInjury(String playerId) {
        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        if (injuries != null) {
            for (Injury injury : injuries) {
                if (!injury.getRecoveryStatus().equals("Recovered")) {
                    return true;
                }
            }
        }
        return false;
    }

    // NEW: Get clearance status based on injuries
    public String getPlayerClearanceStatus(String playerId) {
        ArrayList<Injury> injuries = playerInjuries.get(playerId);
        if (injuries == null || injuries.isEmpty()) {
            return "GREEN";  // No injuries = cleared
        }

        boolean hasActive = false;
        boolean hasAlmost = false;

        for (Injury injury : injuries) {
            String status = injury.getRecoveryStatus();
            if (status.equals("Recovering") || status.equals("Recovering (Long-term)")) {
                hasActive = true;
            } else if (status.equals("Almost Recovered")) {
                hasAlmost = true;
            }
        }

        if (hasActive) {
            return "RED";
        } else if (hasAlmost) {
            return "YELLOW";
        } else {
            return "GREEN";
        }
    }
}