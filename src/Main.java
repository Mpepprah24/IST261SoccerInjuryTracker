import controller.PlayerController;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Soccer Injury Tracker - Complete Management System ===");
        System.out.println("Launching Player Injury Management System...\n");

        new PlayerController();

        System.out.println("Application running - close window to exit.");
    }
}