package pezzi;

import general.Scacchiera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlfiereTest {

    @Test
    public void moveTest() {

        Alfiere a = new Alfiere('N');
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaP = 0;
        int colonnaP = 2;
        int rigaD = 3;
        int colonnaD = 5;
        int spost = 3;
        scacchieraTest.getScacchiera()[1][3] = null;
        a.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('N');
        scacchieraTest = new Scacchiera();

        rigaP = 0;
        colonnaP = 2;
        rigaD = 2;
        colonnaD = 0;
        spost = 2;
        scacchieraTest.getScacchiera()[1][1] = null;

        a.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('B');
        scacchieraTest = new Scacchiera();

        rigaP = 7;
        colonnaP = 5;
        rigaD = 4;
        colonnaD = 2;
        spost = 3;
        scacchieraTest.getScacchiera()[6][4] = null;

        a.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('B');
        scacchieraTest = new Scacchiera();

        rigaP = 7;
        colonnaP = 2;
        rigaD = 3;
        colonnaD = 6;
        spost = 4;
        scacchieraTest.getScacchiera()[6][3] = null;

        a.move(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

    }

    @Test
    public void costruttoreTest() {
        
        Alfiere a = new Alfiere();
        boolean esito = false;
        if (a != null)
            esito = true;        
    }

    @Test
    public void catturaTest() {

        Alfiere a = new Alfiere('N');
        Scacchiera scacchieraTest = new Scacchiera();

        int rigaP = 0;
        int colonnaP = 2;
        int rigaD = 4;
        int colonnaD = 6;
        int spost = 4;
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[4][6] = new Pedone('B');
        scacchieraTest.getScacchiera()[6][6] = null;

        a.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 2;
        rigaD = 2;
        colonnaD = 1;
        spost = 2;
        scacchieraTest.getScacchiera()[1][1] = null;
        scacchieraTest.getScacchiera()[2][1] = new Donna('B');

        a.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('B');
        scacchieraTest = new Scacchiera();

        rigaP = 7;
        colonnaP = 5;
        rigaD = 4;
        colonnaD = 2;
        spost = 3;
        scacchieraTest.getScacchiera()[6][4] = null;
        scacchieraTest.getScacchiera()[4][2] = new Cavallo('N');

        a.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

        a = new Alfiere('B');
        scacchieraTest = new Scacchiera();

        rigaP = 7;
        colonnaP = 2;
        rigaD = 3;
        colonnaD = 6;
        spost = 4;
        scacchieraTest.getScacchiera()[6][3] = null;
        scacchieraTest.getScacchiera()[3][6] = new Torre('N');

        a.cattura(rigaP, colonnaP, rigaD, colonnaD, spost, scacchieraTest);

    }

}
