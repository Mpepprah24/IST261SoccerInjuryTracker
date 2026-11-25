package test;

import model.Team;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    @Test
    public void testConstructor() {
        Team team = new Team("Inter Miami", "Javier Mascherano",
                "Miami, FL", "MLS", "Messi, Suarez, Busquets");
        assertEquals("Inter Miami", team.getTeamName());
        assertEquals("Javier Mascherano", team.getCoach());
    }

    @Test
    public void testSetters() {
        Team team = new Team("Old", "Old", "Old", "Old", "Old");
        team.setTeamName("Napoli");
        team.setCoach("Antonio Conte");
        assertEquals("Napoli", team.getTeamName());
        assertEquals("Antonio Conte", team.getCoach());
    }

    @Test
    public void testToString() {
        Team team = new Team("AC Milan", "Sergio Conceicao",
                "Milan, Italy", "Serie A", "Leao, Pulisic");
        String result = team.toString();
        assertTrue(result.contains("AC Milan"));
        assertTrue(result.contains("Sergio Conceicao"));
    }
}