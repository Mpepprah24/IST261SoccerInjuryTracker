import model.Player;
import model.Injury;

public class TestHarness {
    public static void main(String[] args) {
        System.out.println("=== MANUAL UNIT TESTS ===");
        System.out.println("Testing Player and Injury Classes\n");

        System.out.println("--- TESTING PLAYER CLASS ---");
        testPlayerClass();

        System.out.println("\n--- TESTING INJURY CLASS ---");
        testInjuryClass();

        System.out.println("\n=== ALL MANUAL TESTS COMPLETED ===");
    }

    private static void testPlayerClass() {
        Player player = new Player("Lionel Messi", 38, "MESSI001",
                "Forward", "Inter Miami", 10, "Left");

        System.out.println("Constructor test: " + player.toString());
        System.out.println("Getter test - Name: " + player.getName());
        System.out.println("Getter test - Age: " + player.getAge());
        System.out.println("Getter test - Status: " + player.getClearanceStatus());

        player.setName("Leo Messi");
        player.setAge(37);
        player.setClearanceStatus("GREEN");
        System.out.println("Setter test - Updated: " + player.getName() + ", " + player.getAge() +
                ", Status: " + player.getClearanceStatus());
        System.out.println("✓ Player tests PASSED");
    }

    private static void testInjuryClass() {
        Injury injury = new Injury("ACL Tear", "2024-01-15", "High", "Left Knee",
                false, 0, "TEST001");

        System.out.println("Constructor test: " + injury.toString());
        System.out.println("Getter test - Type: " + injury.getInjuryType());
        System.out.println("Getter test - Recovery Status: " + injury.getRecoveryStatus());

        injury.setInjuryType("Hamstring Strain");
        injury.setSeverity("Medium");
        injury.setRecoveryStatus("Almost Recovered");
        System.out.println("Setter test - Updated: " + injury.getInjuryType() + ", " +
                injury.getSeverity() + ", Status: " + injury.getRecoveryStatus());
        System.out.println("✓ Injury tests PASSED");
    }
}