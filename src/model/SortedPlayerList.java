package model;

import java.util.LinkedList;

/**
 * SortedPlayerList - A LinkedList that maintains players in sorted order by jersey number
 * This demonstrates the LinkedList's efficiency for inserting/removing in the middle
 */
public class SortedPlayerList {
    private LinkedList<PlayerNode> playerList;

    /**
     * Constructor - builds test data in sorted order
     */
    public SortedPlayerList() {
        playerList = new LinkedList<>();
        buildTestData();

        // Output the sorting attribute
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   LINKEDLIST ITEMS SORTED BY ATTRIBUTE: JERSEY NUMBER (ASCENDING)        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");

        // Print initial list
        printList("INITIAL PLAYER LIST (Sorted by Jersey Number)");
    }

    /**
     * Builds test data with 10 players added in sorted order by jersey number
     * Players are added with varying jersey numbers to demonstrate sorting
     */
    private void buildTestData() {
        // Adding players in UNSORTED order to demonstrate the addItem method
        // will insert them in the correct sorted position

        // Player 1: Jersey #10
        addItem(new PlayerNode("MESSI010", "Lionel Messi", 38, "Right Winger",
                "Inter Miami", 10, "Left"));

        // Player 2: Jersey #95
        addItem(new PlayerNode("BASTONI095", "Alessandro Bastoni", 26, "Center Back",
                "Inter Milan", 95, "Left"));

        // Player 3: Jersey #7
        addItem(new PlayerNode("WIRTZ007", "Florian Wirtz", 22, "Midfielder",
                "Liverpool", 7, "Right"));

        // Player 4: Jersey #20
        addItem(new PlayerNode("KUDUS020", "Mohammed Kudus", 25, "Midfielder",
                "Tottenham Hotspur", 20, "Right"));

        // Player 5: Jersey #39
        addItem(new PlayerNode("MCTOMINAY039", "Scott McTominay", 29, "Midfielder",
                "Napoli", 39, "Right"));

        // Player 6: Jersey #9
        addItem(new PlayerNode("DOUE009", "Desire Doue", 20, "Right Winger",
                "PSG", 9, "Right"));

        // Player 7: Jersey #27
        addItem(new PlayerNode("YAMAL027", "Lamine Yamal", 18, "Right Winger",
                "FC Barcelona", 27, "Left"));

        // Player 8: Jersey #14
        addItem(new PlayerNode("LEAO014", "Rafael Leao", 26, "Left Winger",
                "AC Milan", 14, "Right"));

        // Player 9: Jersey #4
        addItem(new PlayerNode("CB004", "Alessandro Bastoni", 26, "Center Back",
                "Inter Milan", 4, "Left"));

        // Player 10: Jersey #22
        addItem(new PlayerNode("HAALAND009", "Erling Haaland", 24, "Striker",
                "Manchester City", 9, "Right"));

        // Note: Jersey #9 will now have two players (Desire Doue and Erling Haaland)
        // They will be placed in order of addition after the same jersey number
    }

    /**
     * Adds a new player to the list in sorted order by jersey number
     * Uses LinkedList's list iterator for efficient insertion
     * @param newPlayer The PlayerNode to add
     */
    public void addItem(PlayerNode newPlayer) {
        if (playerList.isEmpty()) {
            playerList.add(newPlayer);
            return;
        }

        // Find the correct position to insert (maintaining sorted order)
        int insertIndex = 0;
        for (PlayerNode current : playerList) {
            if (newPlayer.compareTo(current) < 0) {
                // New player has smaller jersey number, insert before current
                playerList.add(insertIndex, newPlayer);
                return;
            }
            insertIndex++;
        }

        // If we reach here, new player has the largest jersey number
        playerList.add(newPlayer);
    }

    /**
     * Removes a player from the list by player ID
     * @param searchTerm The player ID to search for and remove
     * @return true if player was found and removed, false otherwise
     */
    public boolean removeItem(String searchTerm) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayerId().equalsIgnoreCase(searchTerm)) {
                playerList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns a player by player ID
     * @param searchTerm The player ID to search for
     * @return The PlayerNode if found, null otherwise
     */
    public PlayerNode getItem(String searchTerm) {
        for (PlayerNode player : playerList) {
            if (player.getPlayerId().equalsIgnoreCase(searchTerm)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Prints the entire list to console in a formatted table
     * @param title The title to display before the list
     */
    public void printList(String title) {
        System.out.println("\n" + "=" .repeat(80));
        System.out.println("  " + title);
        System.out.println("=" .repeat(80));

        if (playerList.isEmpty()) {
            System.out.println("  [EMPTY LIST]");
            System.out.println("=" .repeat(80));
            return;
        }

        // Print table header
        System.out.println("  -------------------------------------------------------------------------");
        System.out.println("  | Jersey | Name                 | Position    | Team                | Age | ID        | Foot  |");
        System.out.println("  -------------------------------------------------------------------------");

        // Print each player
        for (PlayerNode player : playerList) {
            System.out.printf("  | %-6d | %-20s | %-11s | %-20s | %-3d | %-9s | %-5s |\n",
                    player.getJerseyNumber(),
                    player.getName().length() > 20 ? player.getName().substring(0, 17) + "..." : player.getName(),
                    player.getPosition(),
                    player.getTeam().length() > 20 ? player.getTeam().substring(0, 17) + "..." : player.getTeam(),
                    player.getAge(),
                    player.getPlayerId(),
                    player.getPreferredFoot());
        }

        System.out.println("  -------------------------------------------------------------------------");
        System.out.println("  Total Players: " + playerList.size());
        System.out.println("=" .repeat(80));
    }

    /**
     * Gets the size of the list
     * @return Number of players in the list
     */
    public int size() {
        return playerList.size();
    }

    /**
     * Checks if the list is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return playerList.isEmpty();
    }

    /**
     * Gets the first player in the list (smallest jersey number)
     * @return First PlayerNode or null if empty
     */
    public PlayerNode getFirst() {
        return playerList.isEmpty() ? null : playerList.getFirst();
    }

    /**
     * Gets the last player in the list (largest jersey number)
     * @return Last PlayerNode or null if empty
     */
    public PlayerNode getLast() {
        return playerList.isEmpty() ? null : playerList.getLast();
    }

    /**
     * Gets the player at a specific index
     * @param index The index to retrieve
     * @return PlayerNode at index or null if out of bounds
     */
    public PlayerNode get(int index) {
        if (index < 0 || index >= playerList.size()) {
            return null;
        }
        return playerList.get(index);
    }
}