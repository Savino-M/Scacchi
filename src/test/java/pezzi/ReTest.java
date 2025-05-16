package pezzi;

import general.Scacchiera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReTest {

    @Test
    void testRe() {
        
        Re r = new Re('N');
        Re r2 = new Re('B');
        
    }

    @Test
    void testMove() {
        
        Re re;
        int rigaPartenza = 7;
        int colPartenza = 4;
        int rigaDestinazione = 6;
        int colDestinazione = 4;
        Scacchiera scacchieraTest;
        Scacchiera scacchieraAttesa;

        scacchieraTest = new Scacchiera();
        scacchieraAttesa = new Scacchiera();

        scacchieraTest.getScacchiera()[6][4] = null;

        scacchieraAttesa.getScacchiera()[7][4] = null;
        scacchieraAttesa.getScacchiera()[6][4] = new Re('B');
        re = (Re) scacchieraTest.getScacchiera()[7][4];

        // Test movimento Re bianco
        re.move(rigaPartenza, colPartenza, rigaDestinazione, colDestinazione, scacchieraTest);

        rigaPartenza = 0;
        colPartenza = 4;
        rigaDestinazione = 0;
        colDestinazione = 5;

        scacchieraAttesa = new Scacchiera();
        scacchieraAttesa.getScacchiera()[0][4] = null;
        scacchieraAttesa.getScacchiera()[0][5] = new Re('N');

        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[0][5] = null;

        re = (Re) scacchieraTest.getScacchiera()[0][4];

        // Test movimento Re nero
        re.move(rigaPartenza, colPartenza, rigaDestinazione, colDestinazione, scacchieraTest);

    }

    @Test
    void testCattura() {

        Re re;
        int rigaPartenza = 7;
        int colPartenza = 4;
        int rigaTarg = 6;
        int colTarg = 5;
        Scacchiera scacchieraTest;

        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[6][5] = new Pedone('N');

        re = (Re) scacchieraTest.getScacchiera()[7][4];

        // Test cattura da parte di un Re bianco
        re.cattura(rigaPartenza, colPartenza, rigaTarg, colTarg, scacchieraTest);

    }

    @Test
    void testMoveArrocco() {
        
        Re re;
        String giocatore = "bianco";
        int tipoArrocco = 0;
        Scacchiera scacchieraTest;

        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][5] = null;
        scacchieraTest.getScacchiera()[7][6] = null;

        re = (Re) scacchieraTest.getScacchiera()[7][4];

        // Test arrocco corto del Re bianco
        re.moveArrocco(giocatore, scacchieraTest, tipoArrocco);

        giocatore = "nero";
        tipoArrocco = 1;

        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[0][1] = null;
        scacchieraTest.getScacchiera()[0][2] = null;
        scacchieraTest.getScacchiera()[0][3] = null;

        re = (Re) scacchieraTest.getScacchiera()[0][4];

        // Test arrocco lungo del Re nero
        re.moveArrocco(giocatore, scacchieraTest, tipoArrocco);

    }

}
