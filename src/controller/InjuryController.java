package controller;

import model.Injury;
import model.InjuryManager;
import view.InjuryManagementView;

/**
 * InjuryController - MVC Controller for Injury Management
 * Manages the HashMap-based injury collection
 */
public class InjuryController {
    private InjuryManager injuryManager;
    private InjuryManagementView view;

    public InjuryController() {
        injuryManager = new InjuryManager();
        view = new InjuryManagementView(this);
        view.setVisible(true);
    }

    public InjuryManager getInjuryManager() {
        return injuryManager;
    }

    public String getPlayerInjuries(String playerId) {
        if (playerId == null || playerId.trim().isEmpty()) {
            return "❌ Please enter a Player ID";
        }

        java.util.List<Injury> injuries = injuryManager.getPlayerInjuries(playerId);
        if (injuries.isEmpty()) {
            return "⚠️ No injuries found for Player ID: " + playerId;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🏃 Player ID: ").append(playerId).append("\n");
        sb.append("=============================================\n");
        for (int i = 0; i < injuries.size(); i++) {
            sb.append(i + 1).append(". ").append(injuries.get(i).toShortString()).append("\n");
        }
        return sb.toString();
    }

    public String searchInjuries(String playerId, String searchTerm) {
        if (playerId == null || playerId.trim().isEmpty()) {
            return "❌ Please enter a Player ID";
        }
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return "❌ Please enter a search term";
        }

        java.util.List<Injury> results = injuryManager.searchInjuries(playerId, searchTerm);
        if (results.isEmpty()) {
            return "⚠️ No injuries matching '" + searchTerm + "' found for Player ID: " + playerId;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🔍 SEARCH RESULTS for '").append(searchTerm).append("'\n");
        sb.append("=============================================\n");
        sb.append("🏃 Player ID: ").append(playerId).append("\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append(i + 1).append(". ").append(results.get(i).toShortString()).append("\n");
        }
        return sb.toString();
    }

    public String addInjury(String playerId, String injuryType, String injuryDate,
                            String severity, String bodyPart, boolean requiresSurgery,
                            int weeksRecovery) {
        if (playerId == null || playerId.trim().isEmpty()) {
            return "❌ Player ID cannot be empty";
        }
        if (injuryType == null || injuryType.trim().isEmpty()) {
            return "❌ Injury Type cannot be empty";
        }
        if (injuryDate == null || injuryDate.trim().isEmpty()) {
            return "❌ Injury Date cannot be empty";
        }
        if (severity == null || severity.trim().isEmpty()) {
            return "❌ Severity cannot be empty";
        }
        if (bodyPart == null || bodyPart.trim().isEmpty()) {
            return "❌ Body Part cannot be empty";
        }

        Injury newInjury = new Injury(injuryType, injuryDate, severity, bodyPart,
                requiresSurgery, weeksRecovery, playerId);
        injuryManager.addInjury(newInjury);

        return "✅ Injury added successfully for Player ID: " + playerId;
    }

    public String deleteInjury(String playerId, int index) {
        if (playerId == null || playerId.trim().isEmpty()) {
            return "❌ Player ID cannot be empty";
        }

        if (!injuryManager.hasInjuries(playerId)) {
            return "⚠️ No injuries found for Player ID: " + playerId;
        }

        int zeroBasedIndex = index - 1;
        boolean removed = injuryManager.removeInjury(playerId, zeroBasedIndex);

        if (removed) {
            return "✅ Injury #" + index + " deleted successfully for Player ID: " + playerId;
        } else {
            return "❌ Invalid injury number. Please select a valid injury from the list.";
        }
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n📊 INJURY STATISTICS\n");
        sb.append("=============================================\n");
        sb.append("Total Players with Injuries: ").append(injuryManager.getAllPlayerIdsWithInjuries().size()).append("\n");
        sb.append("Total Injuries Recorded: ").append(injuryManager.getTotalInjuryCount()).append("\n");
        sb.append("=============================================");
        return sb.toString();
    }
}