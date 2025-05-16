package pezzi;

import general.Scacchiera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CavalloTest {

    @Test
    public void moveTest() {

        Cavallo c = new Cavallo('B');
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaP = 7;
        int colonnaP = 6;
        int spost = 2;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 7;
        colonnaP = 6;
        spost = 0;
        scacchieraTest.getScacchiera()[6][4] = null;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 7;
        colonnaP = 1;
        spost = 1;
        scacchieraTest.getScacchiera()[6][3] = null;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 1;
        spost = 6;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 1;
        spost = 3;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 6;
        spost = 4;
        scacchieraTest.getScacchiera()[1][4] = null;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 1;
        spost = 5;
        scacchieraTest.getScacchiera()[1][3] = null;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 7;
        colonnaP = 1;
        spost = 7;
        c.move(rigaP, colonnaP, spost, scacchieraTest);

    }

    @Test
    public void catturaTest() {

        Cavallo c = new Cavallo('B');
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaP, colonnaP, spost;

        rigaP = 7;
        colonnaP = 1;
        spost = 2;

        scacchieraTest.getScacchiera()[5][0] = new Pedone('N');
        scacchieraTest.getScacchiera()[1][1] = null;

        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 2;
        colonnaP = 6;
        spost = 0;
        scacchieraTest.getScacchiera()[2][6] = new Cavallo('B');
        scacchieraTest.getScacchiera()[7][6] = null;

        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();

        rigaP = 0;
        colonnaP = 1;
        spost = 6;

        scacchieraTest.getScacchiera()[2][2] = new Torre('B');
        scacchieraTest.getScacchiera()[1][1] = null;

        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();

        rigaP = 0;
        colonnaP = 1;
        spost = 3;

        scacchieraTest.getScacchiera()[2][0] = new Torre('B');
        scacchieraTest.getScacchiera()[1][1] = null;

        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 7;
        colonnaP = 1;
        spost = 1;

        scacchieraTest.getScacchiera()[6][3] = new Pedone('N');

        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 6;
        spost = 4;
        scacchieraTest.getScacchiera()[1][4] = new Pedone('B');
        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('N');
        scacchieraTest = new Scacchiera();
        rigaP = 0;
        colonnaP = 1;
        spost = 5;
        scacchieraTest.getScacchiera()[1][3] = new Donna('B');
        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

        c = new Cavallo('B');
        scacchieraTest = new Scacchiera();
        rigaP = 7;
        colonnaP = 1;
        spost = 7;
        scacchieraTest.getScacchiera()[5][2] = new Alfiere('N');
        c.cattura(rigaP, colonnaP, spost, scacchieraTest);

    }

}
