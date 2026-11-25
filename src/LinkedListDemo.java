import model.PlayerNode;
import model.SortedPlayerList;

public class LinkedListDemo {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SOCCER INJURY TRACKER - M08-A01                         ║");
        System.out.println("║                    LINKEDLIST DEMONSTRATION                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");

        // Instantiate the class containing the LinkedList
        SortedPlayerList sortedPlayers = new SortedPlayerList();

        // ==================== DEMONSTRATION 1: GET ITEM ====================
        System.out.println("\n\n");
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMONSTRATION 1: GETTING AN ITEM FROM THE MIDDLE OF THE LIST             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

        System.out.println("\n🔍 Searching for player with ID: 'WIRTZ007' (Florian Wirtz - Jersey #7)");

        PlayerNode foundPlayer = sortedPlayers.getItem("WIRTZ007");
        if (foundPlayer != null) {
            System.out.println("✅ Player found:");
            System.out.println("   " + foundPlayer.toString());
        } else {
            System.out.println("❌ Player not found!");
        }

        // Show list after get operation (should be unchanged)
        sortedPlayers.printList("LIST AFTER GET OPERATION (Unchanged)");

        // ==================== DEMONSTRATION 2: ADD ITEM ====================
        System.out.println("\n\n");
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMONSTRATION 2: ADDING AN ITEM TO THE MIDDLE OF THE LIST                 │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

        // Adding a player with jersey #15 (between #14 and #20)
        System.out.println("\n➕ Adding player: 'Jude Bellingham' - Jersey #15 (MIDDLE INSERTION)");
        PlayerNode newPlayer = new PlayerNode("BELLINGHAM005", "Jude Bellingham", 21,
                "Midfielder", "Real Madrid", 15, "Right");
        sortedPlayers.addItem(newPlayer);

        // Show list after addition
        sortedPlayers.printList("LIST AFTER ADDING JUDE BELLINGHAM (Jersey #15)");

        // ==================== DEMONSTRATION 3: REMOVE ITEM ====================
        System.out.println("\n\n");
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  DEMONSTRATION 3: REMOVING AN ITEM FROM THE MIDDLE OF THE LIST             │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

        // Removing a player from the middle (jersey #20 - Mohammed Kudus)
        System.out.println("\n❌ Removing player with ID: 'KUDUS020' (Mohammed Kudus - Jersey #20 - MIDDLE)");
        boolean removed = sortedPlayers.removeItem("KUDUS020");
        if (removed) {
            System.out.println("✅ Player removed successfully!");
        } else {
            System.out.println("❌ Player not found!");
        }

        // Show list after removal
        sortedPlayers.printList("LIST AFTER REMOVING MOHAMMED KUDUS (Jersey #20)");

        // ==================== FINAL SUMMARY ====================
        System.out.println("\n\n");
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              DEMONSTRATION SUMMARY                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");

        System.out.println("\n  📊 LinkedList Statistics:");
        System.out.println("     • Total players after all operations: " + sortedPlayers.size());
        System.out.println("     • Smallest jersey number (First): " +
                (sortedPlayers.getFirst() != null ? "#" + sortedPlayers.getFirst().getJerseyNumber() : "N/A"));
        System.out.println("     • Largest jersey number (Last): " +
                (sortedPlayers.getLast() != null ? "#" + sortedPlayers.getLast().getJerseyNumber() : "N/A"));

        System.out.println("\n  ✅ All operations completed successfully!");
        System.out.println("  📌 LinkedList maintains sorted order by Jersey Number at all times.\n");
    }
}