package test;
import static org.junit.jupiter.api.Assertions.*;
import main.Plateau;
import main.Demo;
import main.Bot;
import main.piece.*;
import main.Couleur;


public class ChessWitMeTest {
    
    @org.junit.jupiter.api.Test
    public void testInitialisationPlateau() {

        Plateau plateau = new Plateau(Demo.GAME);
        assertNotNull(plateau);
        assertEquals(8, plateau.getPlateau().length);
        assertEquals(8, plateau.getPlateau()[0].length);
    }

}
