package pezzi;

import general.Scacchiera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DonnaTest
{

    @Test
    void testDonna()
    {
        Donna d = new Donna('N');
        Donna d2 = new Donna('B');

        assertNotNull(d);
        assertNotNull(d2);
    }

    @Test
    void testMoveDiagonale()
    {
        Donna donna;
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaPart = 7;
        int colPart = 3;
        int rigaDest = 4;
        int colDest = 0;
        int spostamento = 3;
        scacchieraTest.getScacchiera()[6][2] = null;
        donna = (Donna) scacchieraTest.getScacchiera()[7][3];

        // Testo movimento diagonale della donna

        assertTrue(donna.moveDiagonale(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 0;
        colPart = 3;
        rigaDest = 2;
        colDest = 1;
        spostamento = 2;
        scacchieraTest.getScacchiera()[1][2] = null;
        donna = (Donna) scacchieraTest.getScacchiera()[0][3];

        assertTrue(donna.moveDiagonale(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 7;
        colPart = 3;
        rigaDest = 4;
        colDest = 6;
        spostamento = 3;
        scacchieraTest.getScacchiera()[6][4] = null;
        donna = (Donna) scacchieraTest.getScacchiera()[7][3];

        assertTrue(donna.moveDiagonale(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

    }

    @Test
    void testCatturaDiagonale()
    {
        Donna donna;
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaPart = 7;
        int colPart = 3;
        int rigaTarget = 4;
        int colTarget = 6;
        int spostamento = 3;
        scacchieraTest.getScacchiera()[6][4] = null;
        scacchieraTest.getScacchiera()[1][6] = null;
        scacchieraTest.getScacchiera()[4][6] = new Pedone('N');
        donna = (Donna) scacchieraTest.getScacchiera()[7][3];

        // Testo la cattura in diagonale

        assertTrue(donna.catturaDiagonale(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 0;
        colPart = 3;
        rigaTarget = 2;
        colTarget = 5;
        spostamento = 2;

        scacchieraTest.getScacchiera()[1][4] = null;
        scacchieraTest.getScacchiera()[2][5] = new Donna('B');
        donna = (Donna) scacchieraTest.getScacchiera()[0][3];

        assertTrue(donna.catturaDiagonale(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 0;
        colPart = 3;
        rigaTarget = 2;
        colTarget = 1;
        spostamento = 2;

        scacchieraTest.getScacchiera()[1][2] = null;
        scacchieraTest.getScacchiera()[2][1] = new Cavallo('B');
        donna = (Donna) scacchieraTest.getScacchiera()[0][3];

        assertTrue(donna.catturaDiagonale(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 7;
        colPart = 3;
        rigaTarget = 2;
        colTarget = 1;
        spostamento = 2;

        scacchieraTest.getScacchiera()[6][2] = null;
        scacchieraTest.getScacchiera()[5][1] = new Torre('B');
        donna = (Donna) scacchieraTest.getScacchiera()[7][3];

        assertTrue(donna.catturaDiagonale(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));


    }

    @Test
    void testMoveLato()
    {
        Donna donna;
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaPart = 5;
        int colPart = 3;
        int rigaDest = 5;
        int colDest = 7;
        int spostamento = 4;
        scacchieraTest.getScacchiera()[7][3] = null;
        scacchieraTest.getScacchiera()[5][3] = new Donna('B');
        donna = (Donna) scacchieraTest.getScacchiera()[5][3];

        // Testo movimento orizzontale

        assertTrue(donna.moveLato(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][3] = null;
        scacchieraTest.getScacchiera()[5][3] = new Donna('B');
        rigaDest = 2;
        colDest = 3;
        spostamento = 3;

        // Testo movimento verticale

        assertTrue(donna.moveLato(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 0;
        colPart = 3;
        rigaDest = 4;
        colDest = 3;
        spostamento = 4;
        scacchieraTest.getScacchiera()[1][3] = null;
        donna = (Donna) scacchieraTest.getScacchiera()[0][3];
        assertTrue(donna.moveLato(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 7;
        colPart = 3;
        rigaDest = 4;
        colDest = 3;
        spostamento = 3;
        scacchieraTest.getScacchiera()[6][3] = null;
        donna = (Donna) scacchieraTest.getScacchiera()[7][3];
        assertTrue(donna.moveLato(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));


        scacchieraTest = new Scacchiera();
        rigaPart = 5;
        colPart = 6;
        rigaDest = 5;
        colDest = 3;
        spostamento = 3;
        scacchieraTest.getScacchiera()[7][3] = null;
        scacchieraTest.getScacchiera()[5][6] = new Donna('B');
        donna = (Donna) scacchieraTest.getScacchiera()[5][6];
        assertTrue(donna.moveLato(rigaPart, colPart, rigaDest, colDest, spostamento, scacchieraTest));

    }

    @Test
    void testCatturaLato()
    {
        Donna donna;
        Scacchiera scacchieraTest = new Scacchiera();
        int rigaPart = 5;
        int colPart = 5;
        int rigaTarget = 5;
        int colTarget = 0;
        int spostamento = 5;
        scacchieraTest.getScacchiera()[7][3] = null;
        scacchieraTest.getScacchiera()[5][5] = new Donna('B');
        scacchieraTest.getScacchiera()[1][0] = null;
        scacchieraTest.getScacchiera()[5][0] = new Pedone('N');
        donna = (Donna) scacchieraTest.getScacchiera()[5][5];

        // Testo cattura orizzontale

        assertTrue(donna.catturaLato(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));


        rigaTarget = 3;
        colTarget = 5;
        spostamento = 2;
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][3] = null;
        scacchieraTest.getScacchiera()[5][5] = new Donna('B');
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[2][5] = new Pedone('N');
        donna = (Donna) scacchieraTest.getScacchiera()[5][5];

        // Testo cattura verticale
        assertTrue(donna.catturaLato(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 0;
        colPart = 3;
        rigaTarget = 2;
        colTarget = 3;
        spostamento = 2;
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[2][3] = new Cavallo('B');
        donna = (Donna) scacchieraTest.getScacchiera()[0][3];

        assertTrue(donna.catturaLato(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

        scacchieraTest = new Scacchiera();
        rigaPart = 5;
        colPart = 3;
        rigaTarget = 5;
        colTarget = 6;
        spostamento = 3;
        scacchieraTest.getScacchiera()[0][3] = null;
        scacchieraTest.getScacchiera()[5][3] = new Donna('N');
        scacchieraTest.getScacchiera()[5][6] = new Alfiere('B');
        donna = (Donna) scacchieraTest.getScacchiera()[5][3];

        assertTrue(donna.catturaLato(rigaPart, colPart, rigaTarget, colTarget, spostamento, scacchieraTest));

    }
}
