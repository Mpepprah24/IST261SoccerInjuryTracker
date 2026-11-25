package test;

import model.Person;
import model.Player;
import model.MedicalStaff;
import model.RecoveryTimeline;
import model.Recoverable;
import java.util.ArrayList;

public class TestInheritInterface {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" Inheritance and Interfaces Test");
        System.out.println("========================================\n");

        testClassHierarchy();
        System.out.println("\n----------------------------------------\n");
        testInterface();

        System.out.println("\n========================================");
        System.out.println("All tests completed successfully!");
    }

    public static void testClassHierarchy() {
        System.out.println("=== PART 1: INHERITANCE TEST ===");
        ArrayList<Person> people = new ArrayList<>();

        // 8 Players
        people.add(new Player("Lionel Messi", 38, "MESSI010", "Right Winger",
                "Inter Miami", 10, "Left"));
        people.add(new Player("Lamine Yamal", 18, "YAMAL027", "Right Winger",
                "FC Barcelona", 10, "Left"));
        people.add(new Player("Mohammed Kudus", 25, "KUDUS020", "Midfielder",
                "Tottenham Hotspur", 20, "Right"));
        people.add(new Player("Florian Wirtz", 22, "WIRTZ007", "Midfielder",
                "Liverpool", 7, "Right"));
        people.add(new Player("Desire Doue", 20, "DOUE009", "Right Winger",
                "PSG", 9, "Right"));
        people.add(new Player("Scott McTominay", 29, "MCTOMINAY039", "Midfielder",
                "Napoli", 39, "Right"));
        people.add(new Player("Rafael Leao", 26, "LEAO010", "Left Winger",
                "AC Milan", 10, "Right"));
        people.add(new Player("Alessandro Bastoni", 26, "BASTONI095", "Center Back",
                "Inter Milan", 95, "Left"));

        // 2 Medical Staff
        people.add(new MedicalStaff("Dr. Sarah Johnson", 45, "MED001", "Team Physician",
                "Sports Medicine", "sarah@hospital.com", "Orthopedics", 15));
        people.add(new MedicalStaff("Dr. James Wilson", 52, "MED002", "Physical Therapist",
                "Rehabilitation", "james@clinic.com", "Physical Therapy", 20));

        System.out.println("Testing " + people.size() + " objects:\n");
        int playerCount = 0;
        int staffCount = 0;

        for (Person p : people) {
            if (p instanceof Player) {
                playerCount++;
                System.out.println("Player #" + playerCount + ":");
            } else {
                staffCount++;
                System.out.println("Medical Staff #" + staffCount + ":");
            }
            System.out.println("  Role: " + p.getRole());
            System.out.println("  Desc: " + p.getDescription() + "\n");
        }

        System.out.println("✓ Tested: " + playerCount + " Players, " + staffCount + " Medical Staff");
    }

    public static void testInterface() {
        System.out.println("=== PART 2: INTERFACE TEST ===");
        ArrayList<Recoverable> items = new ArrayList<>();

        // 4 Injuries
        items.add(new Injury("Hamstring Strain", "2024-02-10", "Medium", "Right Thigh", false, 6));
        items.add(new Injury("Ankle Sprain", "2024-02-15", "Low", "Left Ankle", false, 3));
        items.add(new Injury("Knee Contusion", "2024-02-01", "Medium", "Right Knee", false, 4));
        items.add(new Injury("ACL Tear", "2024-01-05", "High", "Left Knee", true, 36));

        // 4 Recovery Timelines
        items.add(new RecoveryTimeline("2024-02-10", "2024-03-24", "Phase 2: Physical Therapy",
                "Progressing Well", "Lionel Messi", 35));
        items.add(new RecoveryTimeline("2024-02-15", "2024-03-08", "Phase 1: Rest and Protection",
                "Day 3 of Recovery", "Lamine Yamal", 15));
        items.add(new RecoveryTimeline("2024-02-01", "2024-03-01", "Phase 3: Strength Training",
                "Cleared for Light Training", "Mohammed Kudus", 75));
        items.add(new RecoveryTimeline("2024-01-05", "2024-09-05", "Phase 1: Post-Surgery Recovery",
                "Long-Term Rehabilitation", "Florian Wirtz", 20));

        System.out.println("Testing " + items.size() + " objects:\n");
        int injuryCount = 0;
        int timelineCount = 0;

        for (Recoverable r : items) {
            if (r instanceof Injury) {
                injuryCount++;
                System.out.println("Injury #" + injuryCount + ":");
            } else {
                timelineCount++;
                RecoveryTimeline rt = (RecoveryTimeline) r;
                System.out.println("Recovery Timeline for " + rt.getPlayerName() + ":");
            }
            System.out.println("  Recovery Time: " + r.estimateRecoveryTime());
            System.out.println("  Status: " + r.getRecoveryStatus());
            System.out.println("  Plan: " + r.getTreatmentPlan() + "\n");
        }

        System.out.println("✓ Tested: " + injuryCount + " Injuries, " + timelineCount + " Recovery Timelines");
    }
}