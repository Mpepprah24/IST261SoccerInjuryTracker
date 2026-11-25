package test;

import model.RecoveryTimeline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecoveryTimelineTest {
    @Test
    public void testConstructor() {
        RecoveryTimeline timeline = new RecoveryTimeline("2024-01-15", "2024-06-15",
                "Rehabilitation", "Not Cleared",
                "Lionel Messi", 45);
        assertEquals("2024-01-15", timeline.getStartDate());
        assertEquals("Rehabilitation", timeline.getCurrentPhase());
        assertEquals("Lionel Messi", timeline.getPlayerName());
        assertEquals(45, timeline.getPercentageComplete());
    }

    @Test
    public void testSetters() {
        RecoveryTimeline timeline = new RecoveryTimeline("2024-01-01", "2024-03-01",
                "Initial", "Progress", "Player", 0);
        timeline.setCurrentPhase("Strength Training");
        timeline.setPercentageComplete(75);
        assertEquals("Strength Training", timeline.getCurrentPhase());
        assertEquals(75, timeline.getPercentageComplete());
    }

    @Test
    public void testRecoveryMethods() {
        RecoveryTimeline timeline = new RecoveryTimeline("2024-01-15", "2024-09-15",
                "Phase 3", "In Progress",
                "Lionel Messi", 45);
        assertTrue(timeline.estimateRecoveryTime().contains("2024-01-15"));
        assertTrue(timeline.getRecoveryStatus().contains("45%"));
        assertTrue(timeline.getTreatmentPlan().contains("Recovery plan"));
    }
}