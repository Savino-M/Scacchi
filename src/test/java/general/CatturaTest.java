package general;

import org.junit.jupiter.api.Test;
import pezzi.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatturaTest {

    @Test
    void testCatturaRe() {
        
        String comando = "Rxd2";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][0] = null;
        scacchieraTest.getScacchiera()[6][3] = null;
        scacchieraTest.getScacchiera()[6][3] = new Pedone('N');

        // Test cattura in diagonale
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Rxe4";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][4] = null;
        scacchieraTest.getScacchiera()[5][4] = new Re('B');
        scacchieraTest.getScacchiera()[1][4] = null;
        scacchieraTest.getScacchiera()[4][4] = new Pedone('N');

        // Test cattura in verticale
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Rxf3";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][4] = null;
        scacchieraTest.getScacchiera()[5][4] = new Re('B');
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[5][5] = new Pedone('N');

        // Test cattura in orizzontale
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Rxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Rxe7";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[1][4] = null;
        scacchieraTest.getScacchiera()[1][4] = new Pedone('B');
        new Cattura(comando, giocatore, scacchieraTest);
        
    }

    @Test
    void testCatturaCavallo() {
        
        String comando = "Cxf3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[5][5] = new Pedone('N');

        // Test cattura tipo "L verticale"
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Cxd4";
        scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[7][6] = null;
        scacchieraTest.getScacchiera()[5][5] = new Cavallo('B');
        scacchieraTest.getScacchiera()[1][3] = null;
        scacchieraTest.getScacchiera()[4][3] = new Pedone('N');

        // Test cattura tipo "L orizzontale"
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Cxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

    }

    @Test
    void testCatturaAlfiere() {
        
        String comando = "Axf5";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[7][5] = null;
        scacchieraTest.getScacchiera()[5][7] = new Alfiere('B');
        scacchieraTest.getScacchiera()[1][5] = null;
        scacchieraTest.getScacchiera()[3][5] = new Pedone('N');

        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Axf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

    }

    @Test
    void testCatturaTorre() {
        
        String comando = "Txh7";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        scacchieraTest.getScacchiera()[6][7] = null;

        // Test cattura verticale
        new Cattura(comando, giocatore, scacchieraTest);

        scacchieraTest = new Scacchiera();

        comando = "Txc4";

        scacchieraTest.getScacchiera()[7][7] = null;
        scacchieraTest.getScacchiera()[4][5] = new Torre('B');
        scacchieraTest.getScacchiera()[1][2] = null;
        scacchieraTest.getScacchiera()[4][2] = new Pedone('N');
        
        // Test cattura orizzontale
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Txf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

    }

    @Test
    void testCatturaDonna() {
        
        String comando = "Dxd7";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();

        scacchieraTest.getScacchiera()[6][3] = null;

        // Test cattura verticale
        new Cattura(comando, giocatore, scacchieraTest);

        comando = "Cxf3";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

    }

    @Test
    void testCatturaPedone() {
        
        String comando = "hxg3";
        String giocatore = "bianco";
        Scacchiera scacchieraTest = new Scacchiera();
        Scacchiera scacchieraAttesa = new Scacchiera();

        scacchieraTest.getScacchiera()[1][6] = null;
        scacchieraTest.getScacchiera()[5][6] = new Pedone('N');

        scacchieraAttesa.getScacchiera()[1][6] = null;
        scacchieraAttesa.getScacchiera()[6][7] = null;
        scacchieraAttesa.getScacchiera()[5][6] = new Pedone('B');

        new Cattura(comando, giocatore, scacchieraTest);

        comando = "hxb2";
        giocatore = "nero";
        scacchieraTest = new Scacchiera();
        new Cattura(comando, giocatore, scacchieraTest);

    }

}
