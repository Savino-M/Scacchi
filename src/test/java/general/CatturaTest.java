package general;

import org.junit.jupiter.api.Test;
import pezzi.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatturaTest
{

    @Test
    void testCatturaRe()
    {
        String comando = "Rxd2";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][0] = null;
        scacchieraTest.getScacchiera()[6][3] = null;
        scacchieraTest.getScacchiera()[6][3] = new Pedone('N');

        // Test cattura in diagonale

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[7][4]);
        assertTrue(scacchieraTest.getScacchiera()[6][3] instanceof Re);

        comando = "Rxe4";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][4] = null;
        scacchieraTest.getScacchiera()[5][4] = new Re('B');
        scacchieraTest.getScacchiera()[1][4] = null;
        scacchieraTest.getScacchiera()[4][4] = new Pedone('N');

        // Test cattura in verticale

        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[5][4]);
        assertTrue(scacchieraTest.getScacchiera()[4][4] instanceof Re);

        comando = "Rxf3";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][4] = null;
        scacchieraTest.getScacchiera()[5][4] = new Re('B');
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[5][5] = new Pedone('N');

        // Test cattura in orizzontale

        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[5][4]);
        assertTrue(scacchieraTest.getScacchiera()[5][5] instanceof Re);

        comando = "Rxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[0][4] instanceof Re);


        comando = "Rxe7";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][4] = null;
        scacchieraTest.getScacchiera()[1][4] = new Pedone('B');
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[1][4] instanceof Re);
    }

    @Test
    void testCatturaCavallo()
    {
        String comando = "Cxf3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[5][5] = new Pedone('N');

        // Test cattura tipo "L verticale"

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[7][6]);
        assertTrue(scacchieraTest.getScacchiera()[5][5] instanceof Cavallo);

        comando = "Cxd4";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][6] = null;
        scacchieraTest.getScacchiera()[5][5] = new Cavallo('B');
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[4][3] = new Pedone('N');

        // Test cattura tipo "L orizzontale"

        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[5][5]);
        assertTrue(scacchieraTest.getScacchiera()[4][3] instanceof Cavallo);

        comando = "Cxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[0][1] instanceof Cavallo);
        assertTrue(scacchieraTest.getScacchiera()[0][6] instanceof Cavallo);

    }

    @Test
    void testCatturaAlfiere()
    {
        String comando = "Axf5";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[7][5] = null;
        scacchieraTest.getScacchiera()[5][7] = new Alfiere('B');
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[3][5] = new Pedone('N');

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);

        assertNull(scacchieraTest.getScacchiera()[5][7]);
        assertTrue(scacchieraTest.getScacchiera()[3][5] instanceof Alfiere);

        comando = "Axf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[0][2] instanceof Alfiere);
        assertTrue(scacchieraTest.getScacchiera()[0][5] instanceof Alfiere);


    }

    @Test
    void testCatturaTorre()
    {
        String comando = "Txh7";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[6][7] = null;

        // Test cattura verticale

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[7][7]);
        assertTrue(scacchieraTest.getScacchiera()[1][7] instanceof Torre);

        scacchieraTest = new Scacchiera();

        comando = "Txc4";

        scacchieraTest.getScacchiera()[7][7] = null;
        scacchieraTest.getScacchiera()[4][5] = new Torre('B');
        scacchieraTest.getScacchiera()[1][2] = null;
        scacchieraTest.getScacchiera()[4][2] = new Pedone('N');
        // Test cattura orizzontale

        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[4][5]);
        assertTrue(scacchieraTest.getScacchiera()[4][2] instanceof Torre);

        comando = "Txf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[0][0] instanceof Torre);
        assertTrue(scacchieraTest.getScacchiera()[0][7] instanceof Torre);


    }

    @Test
    void testCatturaDonna()
    {
        String comando = "Dxd7";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[6][3] = null;

        // Test cattura verticale

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[7][3]);
        assertTrue(scacchieraTest.getScacchiera()[1][3] instanceof Donna);

        comando = "Cxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[0][3] instanceof Donna);

    }

    @Test
    void testCatturaPedone()
    {
        String comando = "hxg3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        Scacchiera scacchieraAttesa = new Scacchiera();

        scacchieraTest.getScacchiera()[1][6] = null;
        scacchieraTest.getScacchiera()[5][6] = new Pedone('N');

        scacchieraAttesa.getScacchiera()[1][6] = null;
        scacchieraAttesa.getScacchiera()[6][7] = null;
        scacchieraAttesa.getScacchiera()[5][6] = new Pedone('B');

        Cattura catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertNull(scacchieraTest.getScacchiera()[6][7]);
        assertTrue(scacchieraTest.getScacchiera()[5][6] instanceof Pedone);

        comando = "hxb2";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        catturaTest = new Cattura(comando, giocatore, scacchieraTest);
        assertTrue(scacchieraTest.getScacchiera()[6][1] instanceof Pedone);

    }

}
